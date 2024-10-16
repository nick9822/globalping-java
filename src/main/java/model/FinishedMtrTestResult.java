package model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents Mtr measurement test results and it extends {@link BaseTestResult}.
 */
@Getter
@ToString
public class FinishedMtrTestResult extends BaseTestResult {

  String resolvedAddress;
  String resolvedHostname;
  List<MeasurementTestHopExtended> hops;
}
