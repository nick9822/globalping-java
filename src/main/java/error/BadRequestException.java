package error;

public class BadRequestException extends GlobalpingApiError {

  public BadRequestException(String message) {
    super(400, message);
  }
}
