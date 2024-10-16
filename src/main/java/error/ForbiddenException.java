package error;

/**
 * This class represents an exception that occurs on Globalping service when a forbidden
 * resource/operation is performed. It extends {@link GlobalpingApiError}.
 */
public class ForbiddenException extends GlobalpingApiError {

  public ForbiddenException(String message) {
    super(403, message);
  }
}
