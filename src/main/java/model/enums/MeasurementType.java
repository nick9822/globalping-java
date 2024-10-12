package model.enums;

public enum MeasurementType {
  ping("ping"),
  traceroute("traceroute"),
  dns("dns"),
  mtr("mtr"),
  http("http");

  String text;

  MeasurementType(String text) {
    text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
