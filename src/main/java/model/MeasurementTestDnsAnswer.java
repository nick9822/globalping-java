package model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents DNS Answers returned from the DNS measurement tests.
 */
@Getter
@ToString
public class MeasurementTestDnsAnswer {

  /**
   * The record domain name.
   */
  String name;
  /**
   * The record type.
   */
  String type;
  /**
   * The record time-to-live value in seconds.
   */
  Integer ttl;
  /**
   * The record class which comes as a DNS field {@code class}.
   */
  @SerializedName("class")
  String dnsClass;
  /**
   * The record value.
   */
  String value;
}
