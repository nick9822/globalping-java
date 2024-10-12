package model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import model.enums.CustomGson;

public interface MeasurementOption {

  String toJson();

  byte[] toJsonBytes();
}

class MeasurementOptionGeneric implements MeasurementOption {

  JsonElement value;

  MeasurementOptionGeneric(JsonElement v) {
    value = v;
  }

  @Override
  public String toJson() {
    return CustomGson.get().toJson(this);
  }

  @Override
  public byte[] toJsonBytes() {
    return CustomGson.get().toJson(this).getBytes(StandardCharsets.UTF_8);
  }

  public <T> T retrieveMeasurementOption(Class<T> cl) {
    return CustomGson.get().fromJson(this.value, cl);
  }
}

class MeasurementOptionSerializer implements
    JsonSerializer<MeasurementOption>, JsonDeserializer<MeasurementOption> {

  @Override
  public MeasurementOptionGeneric deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    return new MeasurementOptionGeneric(jsonElement);
  }

  @Override
  public JsonElement serialize(MeasurementOption measurementOption, Type type,
      JsonSerializationContext jsonSerializationContext) {
    return jsonSerializationContext.serialize(measurementOption, measurementOption.getClass());
  }
}