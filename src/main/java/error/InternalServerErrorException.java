package error;

public class InternalServerErrorException extends GlobalpingApiError {

  public InternalServerErrorException(String message) {
    super(500, message);
  }
}
