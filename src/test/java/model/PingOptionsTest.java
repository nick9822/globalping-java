package model;

import error.PayloadException;
import model.PingOptions.PingOptionsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PingOptionsTest {

  String expectedDefaultPingOptions = "{\"packets\":3}";
  String expectedAllWithPingOptions = "{\"packets\":8,\"ipVersion\":4}";

  @Test
  @DisplayName("Ping Options with default values")
  public void pingOptions_default_test() {
    PingOptions mtr = new PingOptionsBuilder().build();
    Assertions.assertEquals(expectedDefaultPingOptions, mtr.toJson());
  }

  @Test
  @DisplayName("Ping Options with Packets=8,IP Version=4")
  public void pingOptions_with_packets8_ipV4() {
    PingOptions po = null;
    try {
      po = new PingOptionsBuilder()
          .withIpVersion(4)
          .withPackets(8)
          .build();
    } catch (PayloadException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertEquals(expectedAllWithPingOptions, po.toJson());
  }

  @Test
  @DisplayName("Ping Options with wrong IP version")
  public void pingOptions_withWrongIpVersion() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      PingOptions po = new PingOptionsBuilder().withIpVersion(8).build();
    });
  }

  @Test
  @DisplayName("Ping Options with wrong packets")
  public void pingOptions_withWrongPackets() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      PingOptions po = new PingOptionsBuilder().withPackets(80).build();
    });
  }
}