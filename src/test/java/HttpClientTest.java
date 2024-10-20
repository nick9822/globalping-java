import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import model.GlobalpingRequest;
import model.Probes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WireMockTest(httpPort = 8099)
class HttpClientTest {

  @Test
  @Order(1)
  @DisplayName("HttpClient with good connection")
  void sendRequestGoodConnection(WireMockRuntimeInfo wmRuntimeInfo) {
    Assertions.assertDoesNotThrow(() -> {
      GlobalpingRequest req = new GlobalpingRequest();
      req.setUrl(wmRuntimeInfo.getHttpBaseUrl() + "/v1/probes");
      req.setToken("");
      req.setMethod("GET");

      Probes res = new HttpClient().sendRequest(req, Probes.class);
    });
  }

  @Test
  @Order(2)
  @DisplayName("HttpClient with bad connection")
  void sendRequestBadConnection(WireMockRuntimeInfo wmRuntimeInfo) throws InterruptedException {
    wmRuntimeInfo.getWireMock().shutdown();
    Thread.sleep(1000);
    Assertions.assertThrows(Exception.class, () -> {
      GlobalpingRequest req = new GlobalpingRequest();
      req.setUrl(wmRuntimeInfo.getHttpBaseUrl() + "/v1/probes");
      req.setToken("");
      req.setMethod("GET");

      Probes res = new HttpClient().sendRequest(req, Probes.class);
    });
  }
}