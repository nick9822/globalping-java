package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FinishedSimpleDnsTestResult extends BaseTestResult {

  Integer statusCode;
  String statusCodeName;
  String resolver;
  List<MeasurementTestDnsAnswer> answers;
  Map<String, String> timings;
}
