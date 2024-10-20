import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import error.GlobalpingApiError;
import error.PayloadException;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import model.Limits;
import model.MeasurementLocationOption;
import model.MeasurementLocations;
import model.MeasurementRequest;
import model.MeasurementRequest.MeasurementRequestBuilder;
import model.MeasurementResponse;
import model.MeasurementTarget;
import model.PingOptions.PingOptionsBuilder;
import model.Probes;
import model.enums.MeasurementStatus;
import model.enums.MeasurementType;
import model.enums.RegionName;
import model.enums.TargetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 8099)
class GlobalpingClientTest {

  final String id = "PY5fMsREMmIq45VR";

  @Test
  @DisplayName("GlobalpingClient without token")
  void init_withoutToken() {
    GlobalpingClient gpc = GlobalpingClient.init("http://localhost:8080", "");
    Assertions.assertEquals("http://localhost:8080", gpc.apiUrl);
    Assertions.assertEquals("", gpc.apiToken);
  }

  @Test
  @DisplayName("GlobalpingClient with token")
  void init_withToken() {
    GlobalpingClient gpc = GlobalpingClient.init("http://localhost:8080", "xxxxzzxccxss");
    Assertions.assertEquals("http://localhost:8080", gpc.apiUrl);
    Assertions.assertEquals("xxxxzzxccxss", gpc.apiToken);
  }

  @Test
  @DisplayName("Request measurement with ping options")
  void requestMeasurement_PingOptions(WireMockRuntimeInfo wmRuntimeInfo) {
    Assertions.assertDoesNotThrow(() -> {
      GlobalpingClient gpclient = GlobalpingClient.init(wmRuntimeInfo.getHttpBaseUrl(), "");
      MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
          new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
          .withLocations(new MeasurementLocations(
              Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
                  MeasurementLocationOption.withCountry("JP"))))
          .withMeasurementOptions(new PingOptionsBuilder().withPackets(8).build())
          .build();

      gpclient.requestMeasurement(measurementRequest);
    });
  }

  @Test
  @DisplayName("Fetch measurement of ping options")
  void pollForMeasurement_validPingOptions(WireMockRuntimeInfo wmRuntimeInfo) {
    Assertions.assertDoesNotThrow(() -> {
      GlobalpingClient gpclient = GlobalpingClient.init(wmRuntimeInfo.getHttpBaseUrl(), "");
      MeasurementResponse res1 = gpclient.pollForMeasurement(id);
    });
  }

  @Test
  @DisplayName("(Async) Request measurement with ping options")
  void asyncRequestMeasurement_PingOptions(WireMockRuntimeInfo wmRuntimeInfo)
      throws PayloadException, IOException, GlobalpingApiError {
    AtomicReference<MeasurementStatus> status = new AtomicReference<>(
        MeasurementStatus.IN_PROGRESS);

    GlobalpingClient gpclient = GlobalpingClient.init(wmRuntimeInfo.getHttpBaseUrl(), "");
    MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
        new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
        .withLocations(new MeasurementLocations(
            Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
                MeasurementLocationOption.withCountry("JP"))))
        .withMeasurementOptions(new PingOptionsBuilder().withPackets(8).build())
        .build();

    gpclient.requestAndPollMeasurementAsync(measurementRequest).thenAccept(res -> {
      status.set(res.getStatus());
    }).exceptionally(e -> {
      Assertions.fail("Something went wrong: " + e.getMessage());
      return null;
    });

    while (status.get() == MeasurementStatus.IN_PROGRESS) {

    }
  }

  @Test
  @DisplayName("List probes currently online")
  void getProbes(WireMockRuntimeInfo wmRuntimeInfo) {
    Assertions.assertDoesNotThrow(() -> {
      GlobalpingClient gpclient = GlobalpingClient.init(wmRuntimeInfo.getHttpBaseUrl(), "");
      Probes res1 = gpclient.getProbes();
    });
  }

  @Test
  @DisplayName("Get current rate limits")
  void getLimits(WireMockRuntimeInfo wmRuntimeInfo) {
    wmRuntimeInfo.getWireMock().setSingleScenarioState("Limits", "UNAUTH");
    Assertions.assertDoesNotThrow(() -> {
      GlobalpingClient gpclient = GlobalpingClient.init(wmRuntimeInfo.getHttpBaseUrl(), "");
      Limits res1 = gpclient.getLimits();
    });
  }

  @Test
  @DisplayName("Get current rate authenticated")
  void getLimits_authenticated(WireMockRuntimeInfo wmRuntimeInfo) {
    wmRuntimeInfo.getWireMock().setSingleScenarioState("Limits", "AUTH");
    Assertions.assertDoesNotThrow(() -> {
      GlobalpingClient gpclient = GlobalpingClient.init(wmRuntimeInfo.getHttpBaseUrl(),
          "xxxxzzxccxss");
      Limits res1 = gpclient.getLimits();
    });
  }
}