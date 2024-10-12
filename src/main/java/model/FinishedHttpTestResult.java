package model;

import java.util.Map;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

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
