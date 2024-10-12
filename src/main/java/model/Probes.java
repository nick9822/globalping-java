package model;

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
@JsonAdapter(ProbesTypeAdapter.class)
public class Probes extends GlobalpingResponse {

  List<Probe> probes;

  public Probes(List<Probe> list) {
    probes = list;
  }
}

class ProbesTypeAdapter extends TypeAdapter<Probes> {

  @Override
  public void write(JsonWriter jsonWriter, Probes probes) throws IOException {
    jsonWriter.beginArray();
    for (Probe e : probes.probes) {
      jsonWriter.jsonValue(CustomGson.get().toJson(e));
    }
    jsonWriter.endArray();
  }

  @Override
  public Probes read(JsonReader jsonReader) throws IOException {
    Type listType = new TypeToken<ArrayList<Probe>>() {
    }.getType();
    List<Probe> list = CustomGson.get().fromJson(jsonReader, listType);
    return new Probes(list);
  }
}
