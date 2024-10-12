package model;

import com.google.gson.annotations.JsonAdapter;
import error.ResultException;
import error.ResultException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import model.enums.MeasurementStatus;
import model.enums.MeasurementType;

@ToString
@Getter
public class MeasurementResponse extends GlobalpingResponse {

  String id;
  MeasurementType type;
  MeasurementTarget target;
  MeasurementStatus status;
  Date createdAt;
  Date updatedAt;
  int probesCount;
  List<MeasurementLocationOption> locations;
  Integer limit;
  @JsonAdapter(MeasurementOptionSerializer.class)
  MeasurementOption measurementOptions;
  List<MeasurementResultItem> results;

  public List<FinishedPingTestResult> getPingTestResults() throws ResultException {
    if (type != MeasurementType.ping) {
      throw new ResultException("test is not of type `ping`");
    }
    return results.stream().map(
            (x) -> ((BaseTestResultGeneric) x.result).retrieveTestResult(FinishedPingTestResult.class))
        .collect(Collectors.toList());
  }

  public List<FinishedTracerouteTestResult> getTracerouteTestResults() throws ResultException {
    if (type != MeasurementType.traceroute) {
      throw new ResultException("test is not of type `traceroute`");
    }
    return results.stream().map((x) -> ((BaseTestResultGeneric) x.result).retrieveTestResult(
        FinishedTracerouteTestResult.class)).collect(Collectors.toList());
  }

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

  public List<FinishedMtrTestResult> getMtrTestResults() throws ResultException {
    if (type != MeasurementType.mtr) {
      throw new ResultException("test is not of type `mtr`");
    }
    return results.stream().map(
            (x) -> ((BaseTestResultGeneric) x.result).retrieveTestResult(FinishedMtrTestResult.class))
        .collect(Collectors.toList());
  }

  public List<FinishedHttpTestResult> getHttpTestResults() throws ResultException {
    if (type != MeasurementType.http) {
      throw new ResultException("test is not of type `http`");
    }
    return results.stream().map(
            (x) -> ((BaseTestResultGeneric) x.result).retrieveTestResult(FinishedHttpTestResult.class))
        .collect(Collectors.toList());
  }
}
