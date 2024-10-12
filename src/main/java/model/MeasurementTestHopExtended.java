package model;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MeasurementTestHopExtended extends MeasurementTestHop {

  List<Integer> asn;
  MeasurementTestExtendedStats stats;
}
