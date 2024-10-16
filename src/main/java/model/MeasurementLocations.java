package model;

import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import model.enums.CustomGson;

/**
 * This class represents locations of the measurements. It contains either last measurement id or
 * list of {@link MeasurementLocationOption}. One of the fields is always null in any given
 * instance.
 */
@ToString
@Getter
@JsonAdapter(MeasurementLocationsTypeAdapter.class)
public class MeasurementLocations {

  /**
   * The {@code id} of a previous measurement whose probes you want to reuse. The probes are
   * returned in the same order as in the previous measurement. Measurement type and options are not
   * reused and need to be specified in the request.
   * <p><i>Note: this option only works for the lifetime of the original measurement
   * and will result in a `422` response for expired or invalid `id` values.</i></p>
   */
  String lastMeasurementId;
  List<MeasurementLocationOption> locationOptions;

  private MeasurementLocations() {
  }

  /**
   * Constructor to create an instance with just list of {@link MeasurementLocationOption}.
   *
   * @param locationOptions list of {@link MeasurementLocationOption}
   */
  public MeasurementLocations(List<MeasurementLocationOption> locationOptions) {
    this.locationOptions = locationOptions;
  }

  /**
   * Constructor to create an instance with just last measurement id.
   *
   * @param lastMeasurementId last measurement id
   */
  public MeasurementLocations(String lastMeasurementId) {
    this.lastMeasurementId = lastMeasurementId;
  }
}

/**
 * Gson custom serializer for {@link MeasurementLocations}.
 */
class MeasurementLocationsTypeAdapter extends TypeAdapter<MeasurementLocations> {

  @Override
  public void write(JsonWriter jsonWriter, MeasurementLocations o) throws IOException {
    if (o.lastMeasurementId != null) {
      jsonWriter.value(o.lastMeasurementId);
    } else {
      jsonWriter.beginArray();
      for (MeasurementLocationOption e : o.locationOptions) {
        jsonWriter.jsonValue(CustomGson.get().toJson(e));
      }
      jsonWriter.endArray();
    }
  }

  @Override
  public MeasurementLocations read(JsonReader jsonReader) throws IOException {
    if (jsonReader.peek() == BEGIN_ARRAY) {
      Type listType = new TypeToken<ArrayList<MeasurementLocationOption>>() {
      }.getType();
      List<MeasurementLocationOption> list = CustomGson.get().fromJson(jsonReader, listType);
      return new MeasurementLocations(list);
    } else {
      return new MeasurementLocations(jsonReader.nextString());
    }
  }
}
