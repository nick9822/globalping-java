package model;

import lombok.ToString;

/**
 * Summary (Round-trip time) {@code rtt} and packet loss statistics.
 * <i>Note: All times are in milliseconds.</i>
 */
@ToString
public class MeasurementTestStats {

  /**
   * The lowest {@code rtt} value.
   */
  Double min;
  /**
   * The average {@code rtt} value.
   */
  Double avg;
  /**
   * The highest {@code rtt} value.
   */
  Double max;
  /**
   * The number of packets sent.
   */
  Integer total;
  /**
   * The number of received packets.
   */
  Integer rcv;
  /**
   * The number of dropped packets {@code (total - rcv)}.
   */
  Integer drop;
  /**
   * The percentage of dropped packets.
   */
  Double loss;
}
