package error;

public class UnauthorizedException extends GlobalpingApiError {

  ErrorBody error;

  public UnauthorizedException(String message) {
    super(401, message);
  }
}
