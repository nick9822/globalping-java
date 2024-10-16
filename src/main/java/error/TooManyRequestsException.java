package error;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents an exception that occurs on Globalping service when too many requests are
 * sent triggering rate limiting behaviour. It extends {@link GlobalpingApiError}.
 */
@ToString
@Getter
public class TooManyRequestsException extends GlobalpingApiError {

  ErrorBody error;
  Map<String, List<String>> headers;

  public TooManyRequestsException() {
    super(429, "rate_limit_exceeded");
  }

  public void setHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
  }
}
