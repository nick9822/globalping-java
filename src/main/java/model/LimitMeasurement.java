package model;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an object containing measurements rate limits.
 */
@Getter
@ToString
public class LimitMeasurement {

  RateLimitDetails create;
}
