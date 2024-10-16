package model.enums;

/**
 * Enum representing different types of network measurements supported by the Globalping API. The
 * measurement type.
 */
public enum MeasurementType {
  ping("ping"),
  traceroute("traceroute"),
  dns("dns"),
  mtr("mtr"),
  http("http");

  final String text;

  MeasurementType(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
