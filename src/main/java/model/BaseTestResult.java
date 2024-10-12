package model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.ToString;
import model.enums.CustomGson;

@ToString
@Getter
public class BaseTestResult {

  String status;
  String rawOutput;
}

class BaseTestResultGeneric extends BaseTestResult {

  JsonElement value;

  BaseTestResultGeneric(JsonElement v) {
    value = v;
  }

  public <T> T retrieveTestResult(Class<T> cl) {
    return CustomGson.get().fromJson(this.value, cl);
  }
}

class BaseTestResultSerializer implements
    JsonSerializer<BaseTestResult>, JsonDeserializer<BaseTestResult> {

  @Override
  public BaseTestResultGeneric deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    BaseTestResultGeneric g = new BaseTestResultGeneric(jsonElement);
    JsonObject o = jsonElement.getAsJsonObject();
    g.status = o.get("status").getAsString();
    g.rawOutput = o.get("rawOutput").getAsString();

    return g;
  }

  @Override
  public JsonElement serialize(BaseTestResult testResult, Type type,
      JsonSerializationContext jsonSerializationContext) {
    return jsonSerializationContext.serialize(testResult, testResult.getClass());
  }
}