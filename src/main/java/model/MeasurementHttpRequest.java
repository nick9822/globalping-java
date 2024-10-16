package model;

import java.util.Map;
import lombok.Setter;
import lombok.ToString;
import model.enums.HttpMethod;

/**
 * This class represents Http request properties within the measurement of type {@code http}.
 */
@ToString
@Setter
public class MeasurementHttpRequest {

  /**
   * An optional override for the {@code Host} header. The default value is based on the
   * {@code target}.
   */
  String host;
  /**
   * The path portion of the URL.
   */
  String path;
  /**
   * The query string portion of the URL.
   */
  String query;
  HttpMethod method = HttpMethod.HEAD;
  /**
   * Additional request headers.
   * <p><i>Note: The {@code Host} and {@code User-Agent} are reserved and internally
   * overridden.</i></p>
   */
  Map<String, String> headers;
}
