package model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CreateMeasurementResponse extends GlobalpingResponse {

  String id;
  int probesCount;
}
