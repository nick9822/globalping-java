package model;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FinishedTracerouteTestResult extends BaseTestResult {

  String resolvedAddress;
  String resolvedHostname;
  List<MeasurementTestHop> hops;
}


