package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import lombok.ToString;

@ToString
public class ProbeLocation {

  String continent;
  String region;
  String country;
  String state;
  String city;
  Integer asn;
  String network;
  Double latitude;
  Double longitude;

  ProbeLocation(JsonObject o) {
    this.continent = getAsTOrNull(o.get("continent"), String.class);
    this.region = getAsTOrNull(o.get("region"), String.class);
    this.country = getAsTOrNull(o.get("country"), String.class);
    this.state = getAsTOrNull(o.get("state"), String.class);
    this.city = getAsTOrNull(o.get("city"), String.class);
    this.asn = getAsTOrNull(o.get("asn"), Integer.class);
    this.network = getAsTOrNull(o.get("network"), String.class);
    this.latitude = getAsTOrNull(o.get("latitude"), Double.class);
    this.longitude = getAsTOrNull(o.get("longitude"), Double.class);
  }

  <T extends Comparable<? super T>> T getAsTOrNull(JsonElement o, Class<T> cl) {
    if (o.getClass() == JsonNull.class) {
      return null;
    }
    if (cl == java.lang.String.class) {
      return cl.cast(o.getAsString());
    } else if (cl == java.lang.Integer.class) {
      return cl.cast(o.getAsInt());
    } else if (cl == java.lang.Double.class) {
      return cl.cast(o.getAsDouble());
    }

    return cl.cast(o.getAsString());
  }
}
