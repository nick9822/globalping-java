package model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class HttpTestResultTimings {

  Integer total;
  Integer dns;
  Integer tcp;
  Integer tls;
  Integer firstByte;
  Integer download;
}
