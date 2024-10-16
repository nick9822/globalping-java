package model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents a DNS measurement test with {@code trace} disabled.
 */
@Getter
@ToString
public class FinishedSimpleDnsTestResult extends BaseTestResult {

  /**
   * The DNS <a
   * href="https://www.iana.org/assignments/dns-parameters/dns-parameters.xhtml#table-dns-parameters-6">response
   * code</a>.
   */
  Integer statusCode;
  /**
   * The DNS <a
   * href="https://www.iana.org/assignments/dns-parameters/dns-parameters.xhtml#table-dns-parameters-6">response
   * code name</a>.
   */
  String statusCodeName;
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
