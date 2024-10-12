package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FinishedMtrTestResult extends BaseTestResult {

  String resolvedAddress;
  String resolvedHostname;
  List<MeasurementTestHopExtended> hops;
}
