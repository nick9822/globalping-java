package model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import java.lang.reflect.Type;


/**
 * This class represents Dns measurement test results and holds a generic value of it. Since
 * {@link FinishedDnsTestResult} comes in two variants {@link FinishedSimpleDnsTestResult} and
 * {@link FinishedTraceDnsTestResult}. We can hold generic value here.
 */
@JsonAdapter(FinishedDnsTestResultSerializer.class)
public class FinishedDnsTestResult extends BaseTestResult {

  JsonElement value;

  public FinishedDnsTestResult(JsonElement jsonElement) {
    this.value = jsonElement;
  }
}

/**
 * Gson custom serializer for FinishedDnsTestResult.
 */
class FinishedDnsTestResultSerializer implements
    JsonSerializer<FinishedDnsTestResult>, JsonDeserializer<FinishedDnsTestResult> {

  @Override
  public FinishedDnsTestResult deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    return new FinishedDnsTestResult(jsonElement);
  }

  @Override
  public JsonElement serialize(FinishedDnsTestResult dnsTestResult, Type type,
      JsonSerializationContext jsonSerializationContext) {
    return jsonSerializationContext.serialize(dnsTestResult, dnsTestResult.getClass());
  }
}