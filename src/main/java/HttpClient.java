import error.GlobalpingApiError;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import lombok.Cleanup;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import model.GlobalpingRequest;
import model.GlobalpingResponse;
import model.MeasurementResponse;

public class HttpClient {

  private static HttpURLConnection createConnection(GlobalpingRequest request) throws IOException {
    HttpURLConnection conn = (HttpURLConnection) request.url().openConnection();

    conn.setRequestMethod(request.getMethod());

    System.out.println(request.getMethod());
    if (request.getToken() != null && !request.getToken().isEmpty()) {
      System.out.println("setting token");
      conn.setRequestProperty("Authorization", "Bearer " + request.getToken());
    }

    if (request.getPayload() != null) {
      System.out.println(new String(request.getPayload().toJsonBytes(), StandardCharsets.UTF_8));

      conn.setDoOutput(true);
      conn.setRequestProperty("Content-Type", "application/json");

      @Cleanup
      OutputStream output = conn.getOutputStream();
      output.write(request.getPayload().toJsonBytes());
    }
    return conn;
  }

  private GlobalpingResponse sendHttpRequest(GlobalpingRequest request)
      throws IOException, GlobalpingApiError {
    HttpURLConnection conn = createConnection(request);
    GlobalpingResponse res = new GlobalpingResponse();

    int responseCode = conn.getResponseCode();
    ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

    if (responseCode >= 200 && responseCode < 300) {
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
        int character;
        while ((character = br.read()) != -1) {
          responseStream.write((byte) character);
        }
      }
      res.setStatusCode(responseCode);
      res.setResponse(responseStream.toByteArray());
    } else {
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
        int character;
        while ((character = br.read()) != -1) {
          responseStream.write((byte) character);
        }
      }
      res.setStatusCode(responseCode);
      res.setHeaders(conn.getHeaderFields());
      GlobalpingApiError.raise(responseStream, conn);
    }
    return res;
  }

  public <T extends GlobalpingResponse> T sendRequest(GlobalpingRequest request, Class<T> cl)
      throws IOException, GlobalpingApiError {
    GlobalpingResponse res = sendHttpRequest(request);
    System.out.println(res);
    return res.to(cl);
  }
}
