package error;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents the body of an HTTP Request which resulted in error on the Globalping
 * service.
 */
@ToString
@Getter
public class ErrorBody {

  String message;
  String type;
  Map<String, String> params;
}
