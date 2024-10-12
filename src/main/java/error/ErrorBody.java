package error;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ErrorBody {

  String message;
  String type;
  Map<String, String> params;
}
