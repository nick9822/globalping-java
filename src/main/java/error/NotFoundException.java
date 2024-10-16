package error;

/**
 * This class represents an exception that occurs on Globalping service when a resource/operation is
 * not found. It extends {@link GlobalpingApiError}.
 */
public class NotFoundException extends GlobalpingApiError {

  public NotFoundException(String message) {
    super(404, message);
  }
}
