package error;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ValidationException extends GlobalpingApiError {

  ErrorBody error;

  public ValidationException() {
    super(400, "validation_error");
  }
}
