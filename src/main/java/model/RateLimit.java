package model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RateLimit {

  LimitMeasurement measurements;
}
