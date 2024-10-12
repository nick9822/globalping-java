package model;

import lombok.ToString;

@ToString
public class MeasurementTestStats {

  Double min;
  Double avg;
  Double max;
  Integer total;
  Integer rcv;
  Integer drop;
  Double loss;
}
