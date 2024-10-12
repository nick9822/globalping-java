package model;

import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GlobalpingRequest {

  String method;
  String url;
  String token;
  Map<String, Object> params;
  MeasurementRequest payload;


  public URL url() throws MalformedURLException {
    return new URL(url);
  }
}
