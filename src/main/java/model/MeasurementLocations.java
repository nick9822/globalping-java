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

@ToString
@Getter
@JsonAdapter(MeasurementLocationsTypeAdapter.class)
public class MeasurementLocations {

  String lastMeasurementId;
  List<MeasurementLocationOption> locationOptions;

  public MeasurementLocations(List<MeasurementLocationOption> locationOptions) {
    this.locationOptions = locationOptions;
  }

  public MeasurementLocations(String lastMeasurementId) {
    this.lastMeasurementId = lastMeasurementId;
  }
}

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
