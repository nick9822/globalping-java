package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents DNS hop in DNS measurement tests.
 */
@Getter
@ToString
public class MeasurementTestDnsHop {

  /**
   * The hostname or IP of the resolver that answered the query.
   */
  String resolver;
  /**
   * List of {@link MeasurementTestDnsAnswer}.
   */
  List<MeasurementTestDnsAnswer> answers;
  /**
   * An array containing details for each packet.
   * <i>Note: All times are in milliseconds.</i>
   */
  Map<String, String> timings;
}
