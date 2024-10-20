package model;


import error.PayloadException;
import model.DnsOptions.DnsOptionsBuilder;
import model.enums.DnsQueryType;
import model.enums.MeasurementProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DnsOptionsTest {

  String expectedDefaultDnsOptions = "{\"query\":{\"type\":\"A\"},\"port\":53,\"protocol\":\"UDP\",\"trace\":false}";
  String expectedAllWithDnsOptions = "{\"query\":{\"type\":\"MX\"},\"port\":80,\"protocol\":\"TCP\",\"trace\":true}";

  @Test
  @DisplayName("Dns Options with default values")
  public void dnsOptions_default_test() {
    DnsOptions dnsOptions = new DnsOptionsBuilder().build();
    Assertions.assertEquals(expectedDefaultDnsOptions, dnsOptions.toJson());
  }

  @Test
  @DisplayName("Dns Options with query=MX, port=80, protocol=TCP, Trace=true")
  public void dnsOptions_queryMX_port80_protocolTCP_traceTrue() {
    DnsOptions dnsOptions = null;
    try {
      dnsOptions = new DnsOptionsBuilder()
          .withQuery(DnsQueryType.MX)
          .withPort(80)
          .withProtocol(MeasurementProtocol.TCP)
          .withTrace(true).build();
    } catch (PayloadException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertEquals(expectedAllWithDnsOptions, dnsOptions.toJson());
  }

  @Test
  @DisplayName("Dns Options with wrong port")
  public void dnsOptions_withWrongPort() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      DnsOptions dnsOptions = new DnsOptionsBuilder()
          .withPort(9999999)
          .build();
    });
  }

  @Test
  @DisplayName("Dns Options with wrong IP Version")
  public void dnsOptions_withWrongIpVersion() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      DnsOptions dnsOptions = new DnsOptionsBuilder()
          .withIpVersion(8)
          .build();
    });
  }
}