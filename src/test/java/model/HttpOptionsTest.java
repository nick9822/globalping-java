package model;

import error.PayloadException;
import java.util.HashMap;
import model.HttpOptions.HttpOptionsBuilder;
import model.enums.HttpMethod;
import model.enums.MeasurementHttpProtocol;
import model.enums.TargetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpOptionsTest {

  String expectedDefaultHttpOptions = "{}";
  String expectedAllWithHttpOptions = "{\"request\":{\"host\":\"www.google.com\",\"path\":\"/blog\",\"query\":\"?id\\u003d123\",\"method\":\"GET\",\"headers\":{\"author\":\"nick\"}},\"resolver\":\"www.google.com\",\"port\":80,\"protocol\":\"HTTPS\",\"ipVersion\":4}";

  @Test
  @DisplayName("Http Options with default values")
  public void httpOptions_default_test() {
    HttpOptions httpOptions = new HttpOptionsBuilder().build();
    Assertions.assertEquals(expectedDefaultHttpOptions, httpOptions.toJson());
  }

  @Test
  @DisplayName("Http Options with MR, port=80, protocol=HTTPS, Resolver, IP Version")
  public void httpOptions_withMr_port80_protocolHttps_withResolver_withIpVersion() {
    HttpOptions httpOptions = null;
    try {
      MeasurementHttpRequest mr = new MeasurementHttpRequest();
      mr.setMethod(HttpMethod.GET);
      mr.setQuery("?id=123");
      mr.setHost("www.google.com");
      mr.setPath("/blog");
      mr.setHeaders(new HashMap<String, String>() {{
        put("author", "nick");
      }});

      httpOptions = new HttpOptionsBuilder()
          .withRequest(mr)
          .withPort(80)
          .withProtocol(MeasurementHttpProtocol.HTTPS)
          .withResolver(new MeasurementTarget(TargetType.HostName, "www.google.com"))
          .withIpVersion(4)
          .build();
    } catch (PayloadException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertEquals(expectedAllWithHttpOptions, httpOptions.toJson());
  }

  @Test
  @DisplayName("Http Options with wrong port")
  public void httpOptions_withWrongPort() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      HttpOptions httpOptions = new HttpOptionsBuilder()
          .withPort(9999999)
          .build();
    });
  }

  @Test
  @DisplayName("Http Options with wrong IP Version")
  public void httpOptions_withWrongIpVersion() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      HttpOptions httpOptions = new HttpOptionsBuilder()
          .withIpVersion(8)
          .build();
    });
  }

  @Test
  @DisplayName("Http Options with wrong resolver")
  public void httpOptions_withWrongResolver() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      HttpOptions httpOptions = new HttpOptionsBuilder()
          .withResolver(new MeasurementTarget(TargetType.IPv4, "www.google.com"))
          .build();
    });
  }
}