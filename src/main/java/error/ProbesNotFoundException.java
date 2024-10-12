package error;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ProbesNotFoundException extends GlobalpingApiError {

  ErrorBody error;


  public ProbesNotFoundException() {
    super(422, "no_probes_found");
  }
}
