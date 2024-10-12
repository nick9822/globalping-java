package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.MeasurementHttpProtocol;

@ToString
public class HttpOptions implements MeasurementOption {

  MeasurementHttpRequest request;
  MeasurementTarget resolver;
  Integer port;
  MeasurementHttpProtocol protocol = MeasurementHttpProtocol.HTTPS;
  Integer ipVersion;

  public HttpOptions(HttpOptionsBuilder httpOptionsBuilder) {
    request = httpOptionsBuilder.request;
    resolver = httpOptionsBuilder.resolver;
    port = httpOptionsBuilder.port;
    protocol = httpOptionsBuilder.protocol;
    ipVersion = httpOptionsBuilder.ipVersion;
  }

  @Override
  public String toJson() {
    return CustomGson.get().toJson(this);
  }

  @Override
  public byte[] toJsonBytes() {
    return CustomGson.get().toJson(this).getBytes(StandardCharsets.UTF_8);
  }

  public static class HttpOptionsBuilder {

    MeasurementHttpRequest request;
    MeasurementTarget resolver;
    Integer port;
    MeasurementHttpProtocol protocol;
    Integer ipVersion;

    public HttpOptionsBuilder() {
    }

    public HttpOptionsBuilder withRequest(MeasurementHttpRequest request) {
      this.request = request;
      return this;
    }

    public HttpOptionsBuilder withResolver(MeasurementTarget resolver) {
      this.resolver = resolver;
      return this;
    }

    public HttpOptionsBuilder withPort(int port) throws PayloadException {
      if (port < 0 || port > 65535) {
        throw new PayloadException("port should be in the range of 0 to 65535");
      }
      this.port = port;
      return this;
    }

    public HttpOptionsBuilder withProtocol(MeasurementHttpProtocol protocol)
        throws PayloadException {
      this.protocol = protocol;
      return this;
    }

    public HttpOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    public HttpOptions build() {
      return new HttpOptions(this);
    }
  }
}


