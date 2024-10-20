package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.MeasurementHttpProtocol;

/**
 * Represents the measurement options used when performing a Http type of measurement. This class
 * implements the {@link MeasurementOption} interface with parameters required for the http
 * queries.
 */
@ToString
public class HttpOptions implements MeasurementOption {

  MeasurementHttpRequest request;
  MeasurementTarget resolver;
  /**
   * The port number to use. It must be in the range of 0 to 65535.
   */
  Integer port;
  MeasurementHttpProtocol protocol = MeasurementHttpProtocol.HTTPS;
  /**
   * IP Version 4|6.
   * <p><i>EXPERIMENTAL: The IP version to use. Only allowed if the target is a hostname.</i></p>
   */
  Integer ipVersion;

  /**
   * Constructor which allows creation of {@link HttpOptions} using {@link HttpOptionsBuilder}.
   *
   * @param httpOptionsBuilder {@link HttpOptionsBuilder}
   */
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

  /**
   * This class represents a builder for Http Options.
   */
  public static class HttpOptionsBuilder {

    MeasurementHttpRequest request;
    MeasurementTarget resolver;
    Integer port;
    MeasurementHttpProtocol protocol;
    Integer ipVersion;

    /**
     * Constructor of Builder {@link HttpOptionsBuilder}.
     * <h3>Example Usage:</h3>
     * <pre>{@code
     *    HttpOptions http = new HttpOptionsBuilder().withPort(80).build();
     * }
     * </pre>
     */
    public HttpOptionsBuilder() {
    }

    /**
     * Chain method for setting value of request.
     *
     * @param request {@link MeasurementHttpRequest}
     * @return {@link HttpOptionsBuilder}
     */
    public HttpOptionsBuilder withRequest(MeasurementHttpRequest request) {
      this.request = request;
      return this;
    }

    /**
     * Chain method for setting value of resolver.
     *
     * @param resolver {@link MeasurementTarget}
     * @return {@link HttpOptionsBuilder}
     */
    public HttpOptionsBuilder withResolver(MeasurementTarget resolver) {
      this.resolver = resolver;
      return this;
    }

    /**
     * Chain method for setting value of port.
     *
     * @param port port within the range of 0 to 65535.
     * @return {@link HttpOptionsBuilder}
     * @throws PayloadException if port param is invalid.
     */
    public HttpOptionsBuilder withPort(int port) throws PayloadException {
      if (port < 0 || port > 65535) {
        throw new PayloadException("port should be in the range of 0 to 65535");
      }
      this.port = port;
      return this;
    }

    /**
     * Chain method for setting value of protocol.
     *
     * @param protocol {@link MeasurementHttpProtocol}
     * @return {@link HttpOptionsBuilder}
     */
    public HttpOptionsBuilder withProtocol(MeasurementHttpProtocol protocol) {
      this.protocol = protocol;
      return this;
    }

    /**
     * Chain method for setting value of protocol.
     *
     * @param ipVersion IP Version 4|6
     * @return {@link HttpOptionsBuilder}
     * @throws PayloadException if ipVersion param is invalid.
     */
    public HttpOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    /**
     * Final method in the chain to build {@link HttpOptions} object.
     *
     * @return {@link HttpOptions}
     */
    public HttpOptions build() {
      return new HttpOptions(this);
    }
  }
}


