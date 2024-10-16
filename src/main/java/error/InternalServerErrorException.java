package error;

/**
 * This class represents an internal server error occurred on Globalping service. It extends
 * {@link GlobalpingApiError}.
 */
public class InternalServerErrorException extends GlobalpingApiError {

  public InternalServerErrorException(String message) {
    super(500, message);
  }
}
