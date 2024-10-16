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

/**
 * This class represents a base class for all the measurement results. it contains status and
 * rawOutput which is common for all the measurements.
 */
@ToString
@Getter
public class BaseTestResult {

  /**
   * Status of a Measurement i.e. finished
   */
  String status;
  /**
   * The raw output of the test. Can be presented to users but is not meant to be parsed by
   * clients.
   * <p><i>Note: Please use the test specific values by calling corresponding method for automated
   * processing.</i></p>
   * <p><i>Example: calling {@link MeasurementResponse#getDnsTestResults()} will return in
   * {@link FinishedDnsTestResult} i.e. results of DNS Measurement Tests.</i></p>
   */
  String rawOutput;
}

/**
 * This class represents a variant of BaseTestResult which holds the whole response as JsonElement.
 * It is later used to deserialize test specific BaseTestResult variants.
 */
class BaseTestResultGeneric extends BaseTestResult {

  JsonElement value;

  BaseTestResultGeneric(JsonElement v) {
    value = v;
  }

  public <T> T retrieveTestResult(Class<T> cl) {
    return CustomGson.get().fromJson(this.value, cl);
  }
}

/**
 * Gson custom serializer for BaseTestResult.
 */
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