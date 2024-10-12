package model;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FinishedPingTestResult extends BaseTestResult {

  String resolvedAddress;
  String resolvedHostname;
  MeasurementTestStats stats;
  List<Map<String, Double>> timings;
}
