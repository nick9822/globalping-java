package error;

public class NotFoundException extends GlobalpingApiError {

  public NotFoundException(String message) {
    super(404, message);
  }
}
