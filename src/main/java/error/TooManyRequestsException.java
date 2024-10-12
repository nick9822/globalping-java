package error;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TooManyRequestsException extends GlobalpingApiError {

  ErrorBody error;
  Map<String, List<String>> headers;

  public void setHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
  }

  public TooManyRequestsException() {
    super(429, "rate_limit_exceeded");
  }
}
