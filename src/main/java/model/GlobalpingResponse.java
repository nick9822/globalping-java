package model;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.enums.CustomGson;

/**
 * This class represents a generic response received from a Globalping service.
 * <p>This class holds HTTP headers received from the request and it's HTTP status code.</p>
 */
@ToString
@Getter
@Setter
public class GlobalpingResponse {

  /**
   * HTTP headers received from a response.
   */
  Map<String, List<String>> headers;
  /**
   * HTTP status code.
   */
  int statusCode;
  /**
   * Byte array of the response body.
   */
  private byte[] response;

  /**
   * This method deserializes byte stream into targeted class of given param.
   * <h3>Example Usage:</h3>
   * <pre>{@code
   *    GlobalpingResponse res = sendHttpRequest(request);
   *    return res.to(CreateMeasurementResponse.class);
   * }
   * </pre>
   *
   * @param <T> the type of the response.
   * @param cl  the {@link Class} object representing the type of response expected.
   * @return an instance of {@code T}.
   */
  public <T> T to(Class<T> cl) {
    try (InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(response),
        StandardCharsets.UTF_8)) {
      System.out.println(new String(response, StandardCharsets.UTF_8));
      return CustomGson.get().fromJson(reader, cl);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
