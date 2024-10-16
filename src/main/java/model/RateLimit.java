package model;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an object containing rate limits information.
 */
@ToString
@Getter
public class RateLimit {

  LimitMeasurement measurements;
}
