package error;

/**
 * This class represents an error getting results and it extends {@link Exception}.
 */
public class ResultException extends Exception {

  public ResultException(String message) {
    super(message);
  }
}
