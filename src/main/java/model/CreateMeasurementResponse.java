package model;

import lombok.Getter;
import lombok.ToString;

/**
 * Represents the response for a measurement request, containing the ID of the newly created
 * measurement and the number of probes used.
 * <p>This class extends {@link GlobalpingResponse} and provides additional information</p>
 */
@ToString
@Getter
public class CreateMeasurementResponse extends GlobalpingResponse {

  String id;
  int probesCount;
}
