package error;

public class ForbiddenException extends GlobalpingApiError {

  public ForbiddenException(String message) {
    super(403, message);
  }
}
