package model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * This class extends hops in the measurement results, it contains additional fields along with the
 * extended {@link MeasurementTestHop}.
 */
@ToString
@Getter
public class MeasurementTestHopExtended extends MeasurementTestHop {

  List<Integer> asn;
  MeasurementTestExtendedStats stats;
}
