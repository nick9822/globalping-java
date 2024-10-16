package model;

import com.google.gson.annotations.JsonAdapter;
import error.ResultException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import model.enums.MeasurementStatus;
import model.enums.MeasurementType;

/**
 * This class represents a measurement response from Globalping service. It extends
 * {@link GlobalpingResponse} which holds additional information about the response.
 */
@ToString
@Getter
public class MeasurementResponse extends GlobalpingResponse {

  /**
   * The measurement ID.
   */
  String id;
  MeasurementType type;
  MeasurementTarget target;
  MeasurementStatus status;
  /**
   * The date and time when the measurement was created.
   */
  Date createdAt;
  /**
   * The date and time when the measurement was last updated.
   */
  Date updatedAt;
  /**
   * The actual number of probes that performed the measurement tests.
   * <p>Smaller or equal to {@code limit}, depending on probe availability.</p>
   */
  int probesCount;
  /**
   * List of {@link MeasurementLocationOption} you specified when creating the measurement.
   */
  List<MeasurementLocationOption> locations;
  /**
   * The limit you specified when creating the measurement if different from the default value.
   */
  Integer limit;
  @JsonAdapter(MeasurementOptionSerializer.class)
  MeasurementOption measurementOptions;
  /**
   * List of {@link MeasurementResultItem}.
   */
  List<MeasurementResultItem> results;

  /**
   * Method to get results of measurement type {@code Ping}.
   *
   * @return List of {@link FinishedPingTestResult}
   * @throws ResultException if measurement result is not of type {@code Ping}
   */
  public List<FinishedPingTestResult> getPingTestResults() throws ResultException {
    if (type != MeasurementType.ping) {
      throw new ResultException("test is not of type `ping`");
    }
    return results.stream().map(
            (x) -> ((BaseTestResultGeneric) x.result)
                .retrieveTestResult(FinishedPingTestResult.class))
        .collect(Collectors.toList());
  }

  /**
   * Method to get results of measurement type {@code traceroute}.
   *
   * @return List of {@link FinishedTracerouteTestResult}
   * @throws ResultException if measurement result is not of type {@code traceroute}
   */
  public List<FinishedTracerouteTestResult> getTracerouteTestResults() throws ResultException {
    if (type != MeasurementType.traceroute) {
      throw new ResultException("test is not of type `traceroute`");
    }
    return results.stream().map((x) -> ((BaseTestResultGeneric) x.result).retrieveTestResult(
        FinishedTracerouteTestResult.class)).collect(Collectors.toList());
  }

  /**
   * Method to get results of measurement type {@code dns}.
   *
   * @return List of {@link FinishedTraceDnsTestResult} or {@link FinishedSimpleDnsTestResult}
   * depending on {@link DnsOptions}
   * @throws ResultException if measurement result is not of type {@code dns}
   */
  public <T extends BaseTestResult> List<T> getDnsTestResults() throws ResultException {
    if (type != MeasurementType.dns) {
      throw new ResultException("test is not of type `dns`");
    }
    boolean isTrace = false;
    if (measurementOptions != null) {
      isTrace = ((MeasurementOptionGeneric) measurementOptions).retrieveMeasurementOption(
          DnsOptions.class).trace;
    }

    if (isTrace) {
      return results.stream().map((x) -> (T) ((BaseTestResultGeneric) x.result).retrieveTestResult(
          FinishedTraceDnsTestResult.class)).collect(Collectors.toList());
    } else {
      return results.stream().map((x) -> (T) ((BaseTestResultGeneric) x.result).retrieveTestResult(
          FinishedSimpleDnsTestResult.class)).collect(Collectors.toList());
    }
  }

  /**
   * Method to get results of measurement type {@code mtr}.
   *
   * @return List of {@link FinishedMtrTestResult}
   * @throws ResultException if measurement result is not of type {@code mtr}
   */
  public List<FinishedMtrTestResult> getMtrTestResults() throws ResultException {
    if (type != MeasurementType.mtr) {
      throw new ResultException("test is not of type `mtr`");
    }
    return results.stream().map(
            (x) -> ((BaseTestResultGeneric) x.result)
                .retrieveTestResult(FinishedMtrTestResult.class))
        .collect(Collectors.toList());
  }

  /**
   * Method to get results of measurement type {@code http}.
   *
   * @return List of {@link FinishedHttpTestResult}
   * @throws ResultException if measurement result is not of type {@code http}
   */
  public List<FinishedHttpTestResult> getHttpTestResults() throws ResultException {
    if (type != MeasurementType.http) {
      throw new ResultException("test is not of type `http`");
    }
    return results.stream().map(
            (x) -> ((BaseTestResultGeneric) x.result)
                .retrieveTestResult(FinishedHttpTestResult.class))
        .collect(Collectors.toList());
  }
}
