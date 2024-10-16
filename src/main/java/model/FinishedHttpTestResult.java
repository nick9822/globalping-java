package model;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents Http measurement test results and it extends {@link BaseTestResult}.
 */
@Getter
@ToString
public class FinishedHttpTestResult extends BaseTestResult {

  String rawHeaders;
  String rawBody;
  Boolean truncated;
  Map<String, Object> headers;
  Integer statusCode;
  String statusCodeName;
  String resolvedAddress;
  HttpTestResultTimings timings;
  TlsCertificate tls;
}
