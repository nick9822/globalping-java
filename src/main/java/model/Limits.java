package model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Limits extends GlobalpingResponse {

  RateLimit rateLimit;
  LimitCredit credits;
}
