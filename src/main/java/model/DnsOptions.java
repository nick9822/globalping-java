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

@ToString
public class DnsOptions implements MeasurementOption {

  @JsonAdapter(DnsQueryTypeSerializer.class)
  DnsQueryType query = DnsQueryType.A;
  MeasurementTarget resolver;
  Integer port = 53;
  MeasurementProtocol protocol = MeasurementProtocol.UDP;
  Integer ipVersion;
  Boolean trace = false;

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

  public static class DnsOptionsBuilder {

    DnsQueryType query = DnsQueryType.A;
    MeasurementTarget resolver;
    Integer port = 53;
    MeasurementProtocol protocol = MeasurementProtocol.UDP;
    Integer ipVersion;
    Boolean trace = false;

    public DnsOptionsBuilder() {
    }

    public DnsOptionsBuilder withQuery(DnsQueryType query) {
      this.query = query;
      return this;
    }

    public DnsOptionsBuilder withResolver(MeasurementTarget resolver) {
      this.resolver = resolver;
      return this;
    }

    public DnsOptionsBuilder withPort(int port) throws PayloadException {
      if (port < 0 || port > 65535) {
        throw new PayloadException("port should be in the range of 0 to 65535");
      }
      this.port = port;
      return this;
    }

    public DnsOptionsBuilder withProtocol(MeasurementProtocol protocol) throws PayloadException {
      this.protocol = protocol;
      return this;
    }

    public DnsOptionsBuilder withIpVersion(int ipVersion) throws PayloadException {
      if (ipVersion != 4 && ipVersion != 6) {
        throw new PayloadException("ipVersion should be either 4 or 6");
      }
      this.ipVersion = ipVersion;
      return this;
    }

    public DnsOptionsBuilder withTrace(boolean trace) throws PayloadException {
      this.trace = trace;
      return this;
    }

    public DnsOptions build() {
      return new DnsOptions(this);
    }
  }
}

class DnsQueryTypeSerializer implements JsonSerializer<DnsQueryType> {

  @Override
  public JsonElement serialize(DnsQueryType dnsQueryType, Type type,
      JsonSerializationContext jsonSerializationContext) {
    JsonObject o = new JsonObject();
    o.addProperty("type", dnsQueryType.toString());
    return jsonSerializationContext.serialize(o);
  }
}

