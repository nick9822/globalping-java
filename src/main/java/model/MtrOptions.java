package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.DnsOptions.DnsOptionsBuilder;
import model.HttpOptions.HttpOptionsBuilder;
import model.enums.CustomGson;
import model.enums.MeasurementHttpProtocol;
import model.enums.MeasurementProtocol;

/**
 * Represents the Mtr-specific options used when performing a Mtr type of measurement. This class
 * implements the {@link MeasurementOption} interface with parameters required for the Mtr queries.
 */
@ToString
public class MtrOptions implements MeasurementOption {

  /**
   * The port number to use.
   */
  Integer port = 80;
  MeasurementProtocol protocol = MeasurementProtocol.ICMP;
  /**
   * <i>EXPERIMENTAL: The IP version to use. Only allowed if the target is a hostname.</i>
   */
  Integer ipVersion;
  /**
   * The number of packets to send to each hop. It must be within the range of 1 to 16.
   */
  Integer packets = 3;

  /**
   * Constructor which allows creation of {@link MtrOptions} using {@link MtrOptionsBuilder}.
   *
   * @param mtrOptionsBuilder {@link MtrOptionsBuilder}
   */
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

  /**
   * This class represents a builder for Mtr Options.
   */
  public static class MtrOptionsBuilder {

    Integer port = 80;
    MeasurementProtocol protocol = MeasurementProtocol.ICMP;
    Integer ipVersion;
    Integer packets = 3;

    /**
     * Constructor of Builder {@link MtrOptionsBuilder}.
     * <h3>Example Usage:</h3>
     * <pre>{@code
     *    MtrOptions mtr = new MtrOptionsBuilder().withPort(80).build();
     * }
     * </pre>
     */
    public MtrOptionsBuilder() {
    }

    /**
     * Chain method for setting value of port.
     *
     * @param port int value within the range of 0 to 65535
     * @return {@link MtrOptionsBuilder}
     * @throws PayloadException if port param is invalid
     */
    public MtrOptionsBuilder withPort(int port) throws PayloadException {
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
     * @return {@link MtrOptionsBuilder}
     */
    public MtrOptionsBuilder withProtocol(MeasurementProtocol protocol) {
      this.protocol = protocol;
      return this;
    }

    /**
     * Chain method for setting value of ipVersion.
     *
     * @param ipVersion int value for IP Version 4|6.
     * @return {@link MtrOptionsBuilder}
     * @throws PayloadException if protocol param value is invalid
     *                          <i>EXPERIMENTAL: The IP version to use. Only allowed if the target
     *                          is a hostname.</i>
     */
    public MtrOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    /**
     * Chain method for setting value of packets.
     *
     * @param packets int value within the range of 1 to 16
     * @return {@link MtrOptionsBuilder}
     * @throws PayloadException if packets param is invalid
     */
    public MtrOptionsBuilder withPackets(int packets) throws PayloadException {
      if (packets < 1 || packets > 16) {
        throw new PayloadException("packets should be in the range of 1 to 16");
      }
      this.packets = packets;
      return this;
    }

    /**
     * Final method in the chain to build {@link MtrOptions} object.
     *
     * @return {@link MtrOptions}
     */
    public MtrOptions build() {
      return new MtrOptions(this);
    }
  }
}


