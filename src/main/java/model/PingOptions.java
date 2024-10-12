package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;

@ToString
public class PingOptions implements MeasurementOption {

  Integer packets;
  Integer ipVersion;

  public PingOptions(PingOptionsBuilder pingOptionsBuilder) {
    packets = pingOptionsBuilder.packets;
    ipVersion = pingOptionsBuilder.ipVersion;
  }

  @Override
  public String toJson() {
    return CustomGson.get().toJson(this);
  }

  @Override
  public byte[] toJsonBytes() {
    return CustomGson.get().toJson(this).getBytes(StandardCharsets.UTF_8);
  }

  public static class PingOptionsBuilder {

    Integer packets = 3;
    Integer ipVersion = null;

    public PingOptionsBuilder() {
    }

    public PingOptionsBuilder withPackets(int packets) throws PayloadException {
      if (packets < 1 || packets > 16) {
        throw new PayloadException("packets should be in the range of 1 to 16");
      }
      this.packets = packets;
      return this;
    }

    public PingOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    public PingOptions build() {
      return new PingOptions(this);
    }
  }
}


