package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.MeasurementProtocol;

/**
 * Represents the Traceroute-specific options used when performing a Traceroute type of measurement.
 * This class implements the {@link MeasurementOption} interface with parameters required for the
 * Traceroute queries.
 */
@ToString
public class TracerouteOptions implements MeasurementOption {

  /**
   * The destination port for the data packets.
   */
  Integer port = 80;
  MeasurementProtocol protocol = MeasurementProtocol.ICMP;
  /**
   * <i>EXPERIMENTAL: The IP version to use. Only allowed if the target is a hostname.</i>
   */
  Integer ipVersion;

  /**
   * Constructor which allows creation of {@link TracerouteOptions} using
   * {@link TracerouteOptionsBuilder}.
   *
   * @param tracerouteOptionsBuilder {@link TracerouteOptionsBuilder}
   */
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

  /**
   * This class represents a builder for Traceroute Options.
   */
  public static class TracerouteOptionsBuilder {

    Integer port = 80;
    MeasurementProtocol protocol = MeasurementProtocol.ICMP;
    Integer ipVersion;

    /**
     * Constructor of Builder {@link TracerouteOptionsBuilder}.
     * <h3>Example Usage:</h3>
     * <pre>{@code
     *    TracerouteOptions mtr = new TracerouteOptionsBuilder().withPort(80).build();
     * }
     * </pre>
     */
    public TracerouteOptionsBuilder() {
    }

    /**
     * Chain method for setting value of port.
     *
     * @param port int value within the range of 0 to 65535
     * @return {@link TracerouteOptionsBuilder}
     * @throws PayloadException if port param is invalid
     */
    public TracerouteOptionsBuilder withPort(int port) throws PayloadException {
      if (port < 0 || port > 65535) {
        throw new PayloadException("port should be in the range of 0 to 65535");
      }
      this.port = port;
      return this;
    }

    /**
     * Chain method for setting value of protocol.
     *
     * @param protocol {@link MeasurementProtocol}
     * @return {@link TracerouteOptionsBuilder}
     */
    public TracerouteOptionsBuilder withProtocol(MeasurementProtocol protocol) {
      this.protocol = protocol;
      return this;
    }

    /**
     * Chain method for setting value of ipVersion.
     *
     * @param ipVersion int value for IP Version 4|6.
     * @return {@link TracerouteOptionsBuilder}
     * @throws PayloadException if protocol param value is invalid
     *                          <i>EXPERIMENTAL: The IP version to use. Only allowed if the target
     *                          is a hostname.</i>
     */
    public TracerouteOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    /**
     * Final method in the chain to build {@link TracerouteOptions} object.
     *
     * @return {@link TracerouteOptions}
     */
    public TracerouteOptions build() {
      return new TracerouteOptions(this);
    }
  }
}


