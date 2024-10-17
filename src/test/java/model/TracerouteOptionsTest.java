package model;

import error.PayloadException;
import model.TracerouteOptions.TracerouteOptionsBuilder;
import model.enums.MeasurementProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TracerouteOptionsTest {

  String expectedDefaultTracerouteOptions = "{\"port\":80,\"protocol\":\"ICMP\"}";
  String expectedAllWithTracerouteOptions = "{\"port\":80,\"protocol\":\"ICMP\",\"ipVersion\":4}";

  @Test
  @DisplayName("Traceroute Options with default values")
  public void TracerouteOptions_default_test() {
    TracerouteOptions tops = new TracerouteOptionsBuilder().build();
    Assertions.assertEquals(expectedDefaultTracerouteOptions, tops.toJson());
  }

  @Test
  @DisplayName("Traceroute Options with Port=80,ICMP,IP Version=4")
  public void TracerouteOptions_with_port80_icmp_ipV4() {
    TracerouteOptions tops = null;
    try {
      tops = new TracerouteOptionsBuilder()
          .withIpVersion(4)
          .withProtocol(MeasurementProtocol.ICMP)
          .withPort(80)
          .build();
    } catch (PayloadException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertEquals(expectedAllWithTracerouteOptions, tops.toJson());
  }

  @Test
  @DisplayName("Traceroute Options with wrong port")
  public void TracerouteOptions_withWrongPort() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      TracerouteOptions tops = new TracerouteOptionsBuilder().withPort(9999999).build();
    });
  }

  @Test
  @DisplayName("Traceroute Options with wrong IP version")
  public void TracerouteOptions_withWrongIpVersion() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      TracerouteOptions tops = new TracerouteOptionsBuilder().withIpVersion(8).build();
    });
  }
}