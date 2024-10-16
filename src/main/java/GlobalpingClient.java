import error.GlobalpingApiError;
import java.io.IOException;
import model.CreateMeasurementResponse;
import model.GlobalpingRequest;
import model.Limits;
import model.MeasurementRequest;
import model.MeasurementResponse;
import model.Probes;


/**
 * A client for interacting with the Globalping API.
 * <p>This class holds the API URL and token required to authenticate and make requests.
 * The token is optional but it needs to be provided as blank.</p> Use the static {@code init}
 * method to create a new instance of the client.
 */
public class GlobalpingClient {

  /**
   * URL of Globalping service.
   */
  String apiUrl;
  /**
   * Globalping Auth Token.
   */
  String apiToken;

  private GlobalpingClient() {
  }

  /**
   * This static method provides an instance of GlobalpingClient.
   *
   * <h3>Usage Example:</h3>
   * <pre>{@code
   *        GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");
   *       }
   * </pre>
   *
   * @param apiUrl   URL to the API service
   * @param apiToken API service Token, use "" if not required.
   * @return {@link GlobalpingClient}
   */
  public static GlobalpingClient init(String apiUrl, String apiToken) {
    GlobalpingClient client = new GlobalpingClient();
    client.apiUrl = apiUrl;
    client.apiToken = apiToken;
    return client;
  }

  /**
   * <p>Creates a new measurement with parameters set in the request body. The measurement
   * runs asynchronously. You can retrieve Id from a {@link CreateMeasurementResponse} and use it to
   * retrieve its current state using {@link #pollForMeasurement(String)}.</p>
   * <p>Alternatively, you can retrieve its current state at the URL returned in the
   * {@code Location} header.</p>
   * <h5>Client guidelines</h5>
   * <ul>
   * <li>If the application is running in interactive mode, use {@code withInProgressUpdates(true)}
   * to have the API return partial results as soon as they are available. This allows the
   * user to see the measurement progress in real time.
   * <h6>Example</h6>
   * <pre>{@code
   *   MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
   *    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
   *    .withInProgressUpdates(true)
   *    .withLocations(new MeasurementLocations(
   *    Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA))))
   *    .withMeasurementOptions(new PingOptionsBuilder().build())
   *    .build();
   * }
   * </pre>
   * <ul><li>If the application is interactive by default but also implements a "CI" mode for
   * scripting, do not set the flag in the CI mode.</li></ul></li>
   * <li>To perform multiple measurements using exactly the same probes, create a single measurement
   * first, then pass its {@code id} in the locations field for the other measurements.
   * <h6>Example</h6>
   * <pre>{@code
   *   MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
   *    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
   *    .withLocations(new MeasurementLocations(
   *    Arrays.asList("nzGzfAGL7sZfUs3c", "ozGzfAGL7sZfUs3c")))
   *    .withMeasurementOptions(new PingOptionsBuilder().build())
   *    .build();
   * }
   * </pre>
   * </li>
   * </ul>
   *
   * <h3>Usage Example:</h3>
   * <pre>{@code
   *      GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");
   *
   *      MeasurementRequest measurementRequest= new MeasurementRequestBuilder(MeasurementType.ping,
   *      new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
   *      .withLocations(new MeasurementLocations(
   *            Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA))))
   *      .withMeasurementOptions(new PingOptionsBuilder().build())
   *      .build();
   *
   *      CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);
   *     }
   * </pre>
   *
   * @param request the {@link MeasurementRequest} containing parameters needed to perform the
   *                measurement.
   * @return a {@link CreateMeasurementResponse} with the details of the measurement result.
   * @throws IOException        if the there is an underlying connection I/O.
   * @throws GlobalpingApiError if there is an error on the server.
   */
  CreateMeasurementResponse requestMeasurement(MeasurementRequest request)
      throws IOException, GlobalpingApiError {
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(apiUrl + "/v1/measurements");
    req.setToken(apiToken);
    req.setMethod("POST");
    req.setPayload(request);

    return new HttpClient().sendRequest(req, CreateMeasurementResponse.class);
  }

  /**
   * <p>Returns the status and results of an existing measurement.</p>
   * <p>Tip: A link to this endpoint is returned in the Location response header when creating
   * the measurement.</p>
   * <p><i>Note: Measurements are typically available for up to 7 days after creation.</i></p>
   * <h3>Usage Example:</h3>
   * <pre>{@code
   *      GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");
   *      MeasurementResponse res = gpclient.pollForMeasurement(res.getId());
   *      res.getResults() // get generic results with List<BaseTestResult>
   *      res.getDnsTestResults() // get measurement type related i.e. List<FinishedPingTestResult>
   *      }
   * </pre>
   *
   * @param id the {@link String} measurement one wants to retrieve.
   * @return a {@link MeasurementResponse} with the details of the measurement result.
   * @throws IOException        if the there is an underlying connection I/O
   * @throws GlobalpingApiError if there is an error on the server
   */
  MeasurementResponse pollForMeasurement(String id) throws IOException, GlobalpingApiError {
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(apiUrl + "/v1/measurements/" + id);
    req.setToken(apiToken);
    req.setMethod("GET");

    return new HttpClient().sendRequest(req, MeasurementResponse.class);
  }

  Probes getProbes() throws IOException, GlobalpingApiError {
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(apiUrl + "/v1/probes");
    req.setMethod("GET");

    return new HttpClient().sendRequest(req, Probes.class);
  }

  Limits getLimits() throws IOException, GlobalpingApiError {
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(apiUrl + "/v1/limits");
    req.setToken(apiToken);
    req.setMethod("GET");

    return new HttpClient().sendRequest(req, Limits.class);
  }
}
