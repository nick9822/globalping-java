package model.enums;

import com.google.gson.annotations.SerializedName;

public enum MeasurementStatus {
  @SerializedName("in-progress")
  IN_PROGRESS,
  @SerializedName("finished")
  FINISHED
}
