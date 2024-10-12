package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.MeasurementProtocol;

@ToString
public class MtrOptions implements MeasurementOption {

  Integer port = 80;
  MeasurementProtocol protocol = MeasurementProtocol.ICMP;
  Integer ipVersion;
  Integer packets = 3;

  public MtrOptions(MtrOptionsBuilder mtrOptionsBuilder) {
    port = mtrOptionsBuilder.port;
    protocol = mtrOptionsBuilder.protocol;
    ipVersion = mtrOptionsBuilder.ipVersion;
    packets = mtrOptionsBuilder.packets;
  }

  @Override
  public String toJson() {
    return CustomGson.get().toJson(this);
  }

  @Override
  public byte[] toJsonBytes() {
    return CustomGson.get().toJson(this).getBytes(StandardCharsets.UTF_8);
  }

  public static class MtrOptionsBuilder {

    Integer port = 80;
    MeasurementProtocol protocol = MeasurementProtocol.ICMP;
    Integer ipVersion;
    Integer packets = 3;

    public MtrOptionsBuilder() {
    }

    public MtrOptionsBuilder withPort(int port) throws PayloadException {
      if (port < 0 || port > 65535) {
        throw new PayloadException("port should be in the range of 0 to 65535");
      }
      this.port = port;
      return this;
    }

    public MtrOptionsBuilder withProtocol(MeasurementProtocol protocol) throws PayloadException {
      this.protocol = protocol;
      return this;
    }

    public MtrOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    public MtrOptionsBuilder withPackets(int packets) throws PayloadException {
      if (packets < 1 || packets > 16) {
        throw new PayloadException("packets should be in the range of 1 to 16");
      }
      this.packets = packets;
      return this;
    }

    public MtrOptions build() {
      return new MtrOptions(this);
    }
  }
}


