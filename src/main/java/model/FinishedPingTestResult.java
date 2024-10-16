package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents Ping measurement test results and it extends {@link BaseTestResult}.
 */
@Getter
@ToString
public class FinishedPingTestResult extends BaseTestResult {

  /**
   * The resolved IP address of the {@code target}.
   */
  String resolvedAddress;
  /**
   * The resolved hostname of the {@code target}.
   */
  String resolvedHostname;
  MeasurementTestStats stats;
  /**
   * An array containing details for each packet.
   * <i>Note: All times are in milliseconds.</i>
   */
  List<Map<String, Double>> timings;
}
