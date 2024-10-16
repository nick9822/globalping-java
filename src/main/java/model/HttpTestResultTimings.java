package model;

import lombok.Getter;
import lombok.ToString;

/**
 * Details about the HTTP request times in the response of measurement request of type
 * {@code http}.
 * <p><i>Note: All times are in milliseconds.</i></p>
 */
@ToString
@Getter
public class HttpTestResultTimings {

  /**
   * The total HTTP request time.
   */
  Integer total;
  /**
   * The time required to perform the DNS lookup.
   */
  Integer dns;
  /**
   * The time from performing the DNS lookup to establishing the TCP connection.
   */
  Integer tcp;
  /**
   * The time from establishing the TCP connection to establishing the TLS session.
   */
  Integer tls;
  /**
   * The time from establishing the TCP/TLS connection to the first response byte.
   */
  Integer firstByte;
  /**
   * The time from the first response byte to downloading the entire response.
   */
  Integer download;
}
