package model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MeasurementTestDnsAnswer {

  String name;
  String type;
  Integer ttl;
  @SerializedName("class")
  String dnsClass;
  String value;
}
