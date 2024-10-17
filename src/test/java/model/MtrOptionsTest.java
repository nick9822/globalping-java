package model;

import error.PayloadException;
import model.MtrOptions.MtrOptionsBuilder;
import model.enums.MeasurementProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MtrOptionsTest {

  String expectedDefaultMtrOptions = "{\"port\":80,\"protocol\":\"ICMP\",\"packets\":3}";
  String expectedAllWithMtrOptions = "{\"port\":8000,\"protocol\":\"ICMP\",\"ipVersion\":4,\"packets\":8}";

  @Test
  @DisplayName("Mtr Options with default values")
  public void mtrOptions_default_test() {
    MtrOptions mtr = new MtrOptionsBuilder().build();
    Assertions.assertEquals(expectedDefaultMtrOptions, mtr.toJson());
  }

  @Test
  @DisplayName("Mtr Options with Port=8000,ICMP,Packets=8,IP Version=4")
  public void mtrOptions_with_port8000_icmp_packets8_ipV4() {
    MtrOptions mtr = null;
    try {
      mtr = new MtrOptionsBuilder()
          .withIpVersion(4)
          .withPackets(8)
          .withProtocol(MeasurementProtocol.ICMP)
          .withPort(8000)
          .build();
    } catch (PayloadException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertEquals(expectedAllWithMtrOptions, mtr.toJson());
  }

  @Test
  @DisplayName("Mtr Options with wrong port")
  public void mtrOptions_withWrongPort() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      MtrOptions mtrOptions = new MtrOptionsBuilder().withPort(9999999).build();
    });
  }

  @Test
  @DisplayName("Mtr Options with wrong IP version")
  public void mtrOptions_withWrongIpVersion() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      MtrOptions mtrOptions = new MtrOptionsBuilder().withIpVersion(8).build();
    });
  }

  @Test
  @DisplayName("Mtr Options with wrong packets")
  public void mtrOptions_withWrongPackets() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      MtrOptions mtrOptions = new MtrOptionsBuilder().withPackets(80).build();
    });
  }
}