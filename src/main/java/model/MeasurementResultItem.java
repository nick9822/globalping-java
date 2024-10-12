package model;

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MeasurementResultItem {

  MeasurementResultProbe probe;
  @JsonAdapter(BaseTestResultSerializer.class)
  BaseTestResult result;
}
