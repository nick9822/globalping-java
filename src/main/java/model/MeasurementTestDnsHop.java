package model;

import java.util.Map;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MeasurementTestDnsHop {

  String resolver;
  List<MeasurementTestDnsAnswer> answers;
  Map<String, String> timings;
}
