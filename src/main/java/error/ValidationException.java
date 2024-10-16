package error;

import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an exception that occurs on Globalping service when a resource/operation
 * request is made with incorrect payload. It extends {@link GlobalpingApiError}.
 */
@ToString
@Getter
public class ValidationException extends GlobalpingApiError {

  ErrorBody error;

  public ValidationException() {
    super(400, "validation_error");
  }
}
