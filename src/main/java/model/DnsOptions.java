package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import error.PayloadException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.DnsQueryType;
import model.enums.MeasurementProtocol;

/**
 * Represents the DNS-specific options used when performing a DNS type of measurement. This class
 * implements the {@link MeasurementOption} interface with parameters required for the DNS queries.
 */
@ToString
public class DnsOptions implements MeasurementOption {

  @JsonAdapter(DnsQueryTypeSerializer.class)
  DnsQueryType query = DnsQueryType.A;
  MeasurementTarget resolver;
  Integer port = 53;
  MeasurementProtocol protocol = MeasurementProtocol.UDP;
  Integer ipVersion;
  Boolean trace = false;

  /**
   * Constructor which allows creation of {@link DnsOptions} using {@link DnsOptionsBuilder}.
   *
   * @param dnsOptionsBuilder {@link DnsOptionsBuilder}
   */
  public DnsOptions(DnsOptionsBuilder dnsOptionsBuilder) {
    query = dnsOptionsBuilder.query;
    resolver = dnsOptionsBuilder.resolver;
    port = dnsOptionsBuilder.port;
    protocol = dnsOptionsBuilder.protocol;
    ipVersion = dnsOptionsBuilder.ipVersion;
    trace = dnsOptionsBuilder.trace;
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
   * @see DnsOptionsBuilder#DnsOptionsBuilder
   */
  public static class DnsOptionsBuilder {

    DnsQueryType query = DnsQueryType.A;
    MeasurementTarget resolver;
    Integer port = 53;
    MeasurementProtocol protocol = MeasurementProtocol.UDP;
    Integer ipVersion;
    Boolean trace = false;


    /**
     * Constructor of Builder {@link DnsOptionsBuilder}.
     * <h3>Example Usage:</h3>
     * <pre>{@code
     *    DnsOptions dno = new DnsOptionsBuilder().withQuery(DnsQueryType.MX).build();
     * }
     * </pre>
     */
    public DnsOptionsBuilder() {
    }

    /**
     * Chain method for setting value of query.
     *
     * @param query {@link DnsQueryType}
     * @return {@link DnsOptionsBuilder}
     */
    public DnsOptionsBuilder withQuery(DnsQueryType query) {
      this.query = query;
      return this;
    }

    /**
     * Chain method for setting value of resolver.
     *
     * @param resolver {@link MeasurementTarget}
     * @return {@link DnsOptionsBuilder}
     */
    public DnsOptionsBuilder withResolver(MeasurementTarget resolver) {
      this.resolver = resolver;
      return this;
    }

    /**
     * Chain method for setting value of port.
     *
     * @param port int value within the range of 0 to 65535
     * @return {@link DnsOptionsBuilder}
     * @throws PayloadException if given port value is invalid
     */
    public DnsOptionsBuilder withPort(int port) throws PayloadException {
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
     * @return {@link DnsOptionsBuilder}
     */
    public DnsOptionsBuilder withProtocol(MeasurementProtocol protocol) {
      this.protocol = protocol;
      return this;
    }

    /**
     * Chain method for setting value of ipVersion.
     *
     * @param ipVersion int value for IP Version 4|6.
     * @return {@link DnsOptionsBuilder}
     * @throws PayloadException if given protocol value is invalid
     *                          <i>EXPERIMENTAL: The IP version to use. Only allowed if the target
     *                          is a hostname.</i>
     */
    public DnsOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    /**
     * Chain method for setting value of trace.
     *
     * @param trace true|false
     * @return {@link DnsOptionsBuilder}
     */
    public DnsOptionsBuilder withTrace(boolean trace) {
      this.trace = trace;
      return this;
    }

    /**
     * Final method in the chain to build {@link DnsOptions} object.
     *
     * @return {@link DnsOptions}
     */
    public DnsOptions build() {
      return new DnsOptions(this);
    }
  }
}

/**
 * Gson custom serializer for DnsQueryType. DnsQuery type comes and needs to be sent in a wrapped
 * object like follows:
 * <pre>
 *   {@code
 *   query: {
 *     type: "A"
 *   }
 *  }
 * </pre>
 */
class DnsQueryTypeSerializer implements JsonSerializer<DnsQueryType> {

  @Override
  public JsonElement serialize(DnsQueryType dnsQueryType, Type type,
      JsonSerializationContext jsonSerializationContext) {
    JsonObject o = new JsonObject();
    o.addProperty("type", dnsQueryType.toString());
    return jsonSerializationContext.serialize(o);
  }
}

