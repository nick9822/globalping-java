package model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents a request object used by HttpClient to communicate with Globalping
 * service.
 */
@ToString
@Getter
@Setter
public class GlobalpingRequest {

  /**
   * HTTP Method  (GET, POST, PUT, etc.).
   */
  String method;
  /**
   * URL of Globalping service.
   */
  String url;
  /**
   * Authorization token required to authenticate with the Globalping service.
   */
  String token;
  /**
   * Query params to be sent along the request.
   */
  Map<String, Object> params;
  MeasurementRequest payload;

  /**
   * Instance method to parse the URL into URL object.
   *
   * @return {@link URL} object
   * @throws MalformedURLException if there is a problem with the URL.
   */
  public URL url() throws MalformedURLException {
    return new URL(url);
  }
}
