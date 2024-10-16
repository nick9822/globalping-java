package error;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an exception that occurs on Globalping service when no suitable probes are
 * found. It extends {@link GlobalpingApiError}.
 */
@ToString
@Getter
public class ProbesNotFoundException extends GlobalpingApiError {

  ErrorBody error;

  public ProbesNotFoundException() {
    super(422, "no_probes_found");
  }
}
