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

@ToString
@Getter
@Setter
public class GlobalpingResponse {

  Map<String, List<String>> headers;
  int statusCode;
  byte[] response;

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
