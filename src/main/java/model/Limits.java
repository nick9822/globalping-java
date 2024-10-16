package model;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents limits which are part of the measurement response.
 */
@Getter
@ToString
public class Limits extends GlobalpingResponse {

  RateLimit rateLimit;
  LimitCredit credits;
}
