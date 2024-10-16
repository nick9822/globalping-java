package model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents a DNS measurement test with {@code trace} enabled.
 */
@Getter
@ToString
public class FinishedTraceDnsTestResult extends BaseTestResult {

  List<MeasurementTestDnsHop> hops;
}
