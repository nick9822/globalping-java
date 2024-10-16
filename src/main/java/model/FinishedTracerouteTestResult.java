package model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents Traceroute measurement test results and it extends {@link BaseTestResult}.
 */
@Getter
@ToString
public class FinishedTracerouteTestResult extends BaseTestResult {

  /**
   * The resolved IP address of the {@code target}.
   */
  String resolvedAddress;
  /**
   * The resolved hostname of the {@code target}.
   */
  String resolvedHostname;
  /**
   * A list containing details about each hop {@link MeasurementTestHop}.
   */
  List<MeasurementTestHop> hops;
}


