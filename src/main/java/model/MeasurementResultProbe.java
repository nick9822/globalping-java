package model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@ToString
@JsonAdapter(MeasurementResultProbeDeserializer.class)
public class MeasurementResultProbe {

  ProbeLocation location;
  List<String> tags;
  List<String> resolvers;
}

class MeasurementResultProbeDeserializer implements JsonDeserializer<MeasurementResultProbe> {

  @Override
  public MeasurementResultProbe deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    JsonObject o = jsonElement.getAsJsonObject();

    MeasurementResultProbe mrp = new MeasurementResultProbe();
    mrp.location = new ProbeLocation(o);
    for (JsonElement e : o.get("tags").getAsJsonArray().asList()) {
      if (mrp.tags == null) {
        mrp.tags = new ArrayList<>();
      }
      mrp.tags.add(e.getAsString());
    }
    for (JsonElement e : o.get("resolvers").getAsJsonArray().asList()) {
      if (mrp.resolvers == null) {
        mrp.resolvers = new ArrayList<>();
      }
      mrp.resolvers.add(e.getAsString());
    }
    return mrp;
  }
}
