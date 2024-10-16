package error;

/**
 * This class represents an exception that occurs on Globalping service when a resource/operation
 * needs an authorization and none provided. It extends {@link GlobalpingApiError}.
 */
public class UnauthorizedException extends GlobalpingApiError {

  ErrorBody error;

  public UnauthorizedException(String message) {
    super(401, message);
  }
}
