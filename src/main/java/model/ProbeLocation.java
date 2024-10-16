package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import lombok.ToString;

/**
 * This class represents Location property of a Globalping Probe.
 */
@ToString
public class ProbeLocation {

  /**
   * A two-letter continent code.
   */
  String continent;
  /**
   * A geographic region name based on UN <a href="https://unstats.un.org/unsd/
   * methodology/m49/">[Standard Country or Area Codes for Statistical Use (M49)]</a>.
   */
  String region;
  /**
   * A two-letter country code based on <a
   * href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements">ISO
   * 3166-1 alpha-2</a>.
   */
  String country;
  /**
   * A two-letter <a
   * href="https://www.faa.gov/air_traffic/publications/atpubs/cnt_html/appendix_a.html">US state
   * code</a>.
   */
  String state;
  /**
   * A city name in English.
   */
  String city;
  /**
   * An autonomous system number (ASN).
   */
  Integer asn;
  /**
   * A network name, such as "Google LLC" or "DigitalOcean, LLC".
   */
  String network;
  /**
   * The latitude of probe location.
   */
  Double latitude;
  /**
   * The longitude of probe location.
   */
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

  @SuppressWarnings("CheckStyle")
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
