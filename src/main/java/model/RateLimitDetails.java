package model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RateLimitDetails {

  String type;
  Integer limit;
  Integer remaining;
  Integer reset;
}
