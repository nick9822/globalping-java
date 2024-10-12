package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.MeasurementProtocol;

@ToString
public class TracerouteOptions implements MeasurementOption {

  Integer port = 80;
  MeasurementProtocol protocol = MeasurementProtocol.ICMP;
  Integer ipVersion;

  public TracerouteOptions(TracerouteOptionsBuilder tracerouteOptionsBuilder) {
    port = tracerouteOptionsBuilder.port;
    protocol = tracerouteOptionsBuilder.protocol;
    ipVersion = tracerouteOptionsBuilder.ipVersion;
  }

  @Override
  public String toJson() {
    return CustomGson.get().toJson(this);
  }

  @Override
  public byte[] toJsonBytes() {
    return CustomGson.get().toJson(this).getBytes(StandardCharsets.UTF_8);
  }

  public static class TracerouteOptionsBuilder {

    Integer port = 80;
    MeasurementProtocol protocol = MeasurementProtocol.ICMP;
    Integer ipVersion;

    public TracerouteOptionsBuilder() {
    }

    public TracerouteOptionsBuilder withPort(int port) throws PayloadException {
      if (port < 0 || port > 65535) {
        throw new PayloadException("port should be in the range of 0 to 65535");
      }
      this.port = port;
      return this;
    }

    public TracerouteOptionsBuilder withProtocol(MeasurementProtocol protocol)
        throws PayloadException {
      this.protocol = protocol;
      return this;
    }

    public TracerouteOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    public TracerouteOptions build() {
      return new TracerouteOptions(this);
    }
  }
}


