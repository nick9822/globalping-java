package model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum representing status of measurement requests made to the Globalping service.
 */
public enum MeasurementStatus {
  @SerializedName("in-progress")
  IN_PROGRESS,
  @SerializedName("finished")
  FINISHED
}
