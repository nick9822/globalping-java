import error.GlobalpingApiError;
import java.io.IOException;
import model.CreateMeasurementResponse;
import model.GlobalpingRequest;
import model.GlobalpingResponse;
import model.Limits;
import model.MeasurementRequest;
import model.MeasurementResponse;
import model.Probes;


public class GlobalpingClient {

  String api_url;
  String api_token;

  public static GlobalpingClient init(String api_url, String api_token) {
    GlobalpingClient client = new GlobalpingClient();
    client.api_url = api_url;
    client.api_token = api_token;
    return client;
  }

  CreateMeasurementResponse requestMeasurement(MeasurementRequest request)
      throws IOException, GlobalpingApiError {
    HttpClient httpClient = new HttpClient();
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(api_url + "/v1/measurements");
    req.setToken(api_token);
    req.setMethod("POST");
    req.setPayload(request);

    return httpClient.sendRequest(req, CreateMeasurementResponse.class);
  }

  MeasurementResponse pollForMeasurement(String id) throws IOException, GlobalpingApiError {
    HttpClient httpClient = new HttpClient();
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(api_url + "/v1/measurements/" + id);
    req.setToken(api_token);
    req.setMethod("GET");

    return httpClient.sendRequest(req, MeasurementResponse.class);
  }

  Probes getProbes() throws IOException, GlobalpingApiError {
    HttpClient httpClient = new HttpClient();
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(api_url + "/v1/probes");
    req.setMethod("GET");

    return httpClient.sendRequest(req, Probes.class);
  }

  Limits getLimits() throws IOException, GlobalpingApiError {
    HttpClient httpClient = new HttpClient();
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(api_url + "/v1/limits");
    req.setToken(api_token);
    req.setMethod("GET");

    return httpClient.sendRequest(req, Limits.class);
  }
}
