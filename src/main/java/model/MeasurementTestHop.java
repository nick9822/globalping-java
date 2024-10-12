package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MeasurementTestHop {

  String resolvedAddress;
  String resolvedHostname;
  List<Map<String, Double>> timings;
}
