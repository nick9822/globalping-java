package model;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an object containing rate limit details.
 */
@Getter
@ToString
public class RateLimitDetails {

  /**
   * Type of the rate limit.
   */
  String type;
  /**
   * The number of rate limit points available in a given time window.
   */
  Integer limit;
  /**
   * The number of rate limit points remaining in the current time window.
   */
  Integer remaining;
  /**
   * The number of seconds until the limit resets.
   */
  Integer reset;
}
