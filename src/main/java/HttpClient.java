import error.GlobalpingApiError;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import lombok.Cleanup;
import model.GlobalpingRequest;
import model.GlobalpingResponse;

/**
 * This class represents an object which managed raw HTTP connection. It does not hold any values
 * but contains critical methods to manage HTTP connection, request and responses.
 */
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
      throw new GlobalpingApiError(responseStream, conn);
    }
    return res;
  }

  /**
   * The only public method in this class sends a request and parses the response into expected
   * class of type parameter.
   * <h3>Example Usage:</h3>
   * <pre>{@code
   *    HttpClient client = new HttpClient();
   *    CreateMeasurementResponse res = client.sendRequest(req, CreateMeasurementResponse.class);
   * }
   * </pre>
   *
   * @param <T>     the type of the response, which must extend {@link GlobalpingResponse}.
   * @param request the {@link GlobalpingRequest} with required information.
   * @param cl      the {@link Class} object representing the type of response expected.
   * @return an instance of the response type {@code T}, deserialized from the API response.
   * @throws IOException        if there is an error on underlying connection.
   * @throws GlobalpingApiError if there is an error on the Globalping service.
   */
  public <T extends GlobalpingResponse> T sendRequest(GlobalpingRequest request, Class<T> cl)
      throws IOException, GlobalpingApiError {
    GlobalpingResponse res = sendHttpRequest(request);
    System.out.println(res);
    return res.to(cl);
  }
}
