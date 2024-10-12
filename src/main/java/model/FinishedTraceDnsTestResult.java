package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FinishedTraceDnsTestResult extends BaseTestResult {

  List<MeasurementTestDnsHop> hops;
}
