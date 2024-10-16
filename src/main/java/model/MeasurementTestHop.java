package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents a general hop in the measurements of types like traceroute, mtr, etc.
 */
@ToString
@Getter
public class MeasurementTestHop {

  /**
   * The resolved IP address of the {@code target}.
   */
  String resolvedAddress;
  /**
   * The resolved hostname of the {@code target}.
   */
  String resolvedHostname;
  /**
   * An array containing details for each packet.
   * <i>Note: All times are in milliseconds.</i>
   */
  List<Map<String, Double>> timings;
}
