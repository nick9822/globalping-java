package model;

import java.util.Map;
import lombok.Setter;
import lombok.ToString;
import model.enums.HttpMethod;

@ToString
@Setter
public class MeasurementHttpRequest {

  String host;
  String path;
  String query;
  HttpMethod method = HttpMethod.HEAD;
  Map<String, String> headers;
}
