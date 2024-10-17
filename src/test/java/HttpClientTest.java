import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import error.GlobalpingApiError;
import java.io.IOException;
import java.net.HttpURLConnection;
import model.GlobalpingRequest;
import model.Limits;
import model.MeasurementResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 8099)
class HttpClientTest {

  static void setUpMockServer() {
    System.out.println("setting up stub");
    stubFor(get("/v1/limits")
//        .withHeader("Content-Type", containing("application/json"))
        .willReturn(ok()
            .withHeader("Content-Type", "application/json")
            .withBody(
                "{ \"rateLimit\": { \"measurements\": { \"create\": { \"type\": \"ip\", \"limit\": 100, \"remaining\": 95, \"reset\": 3599 } } } }")));
  }

  @Test
  void sendRequest(WireMockRuntimeInfo wmRuntimeInfo)
      throws IOException, GlobalpingApiError, InterruptedException {
    setUpMockServer();
    System.out.println(wmRuntimeInfo);
    GlobalpingRequest req = new GlobalpingRequest();
    req.setUrl(wmRuntimeInfo.getHttpBaseUrl() + "/v1/limits");
    req.setToken("");
    req.setMethod("GET");
//    Thread.sleep(20000);

    Limits res = new HttpClient().sendRequest(req, Limits.class);
    System.out.println(res);
  }
}