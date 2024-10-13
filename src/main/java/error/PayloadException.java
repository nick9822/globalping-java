package error;

/**
 * This class represents error on payload and it extends {@link Exception}
 */
public class PayloadException extends Exception {

  public PayloadException(String message) {
    super(message);
  }
}
