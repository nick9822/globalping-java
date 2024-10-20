package model;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;

/**
 * Represents the Ping-specific options used when performing a Ping type of measurement. This class
 * implements the {@link MeasurementOption} interface with parameters required for the Ping
 * queries.
 */
@ToString
public class PingOptions implements MeasurementOption {

  /**
   * The number of packets to send. Must be in the range of 1 to 16.
   */
  Integer packets;
  /**
   * <i>EXPERIMENTAL: The IP version to use. Only allowed if the target is a hostname.</i>
   */
  Integer ipVersion;

  /**
   * Constructor which allows creation of {@link PingOptions} using {@link PingOptionsBuilder}.
   *
   * @param pingOptionsBuilder {@link PingOptionsBuilder}
   */
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

  /**
   * This class represents a builder for Dns Options.
   *
   * @see PingOptionsBuilder#PingOptionsBuilder
   */
  public static class PingOptionsBuilder {

    Integer packets = 3;
    Integer ipVersion = null;

    /**
     * This class represents a builder for Ping Options.
     */
    public PingOptionsBuilder() {
    }

    /**
     * Chain method for setting value of packets.
     *
     * @param packets int value within the range to 1 to 16.
     * @return {@link PingOptionsBuilder}
     * @throws PayloadException if the packets param is invalid.
     */
    public PingOptionsBuilder withPackets(int packets) throws PayloadException {
      if (packets < 1 || packets > 16) {
        throw new PayloadException("packets should be in the range of 1 to 16");
      }
      this.packets = packets;
      return this;
    }

    /**
     * Chain method for setting value of ipVersion.
     *
     * @param ipVersion int value for IP Version 4|6.
     * @return {@link PingOptionsBuilder}
     * @throws PayloadException if protocol param value is invalid
     *                          <i>EXPERIMENTAL: The IP version to use. Only allowed if the target
     *                          is a hostname.</i>
     */
    public PingOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    /**
     * Final method in the chain to build {@link PingOptions} object.
     *
     * @return {@link PingOptions}
     */
    public PingOptions build() {
      return new PingOptions(this);
    }
  }
}


