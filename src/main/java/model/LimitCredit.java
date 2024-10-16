package model;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an object containing credits information (only for authenticated
 * requests).
 */
@ToString
@Getter
public class LimitCredit {

  /**
   * The number of user's remaining credits.
   */
  Integer remaining;
}
