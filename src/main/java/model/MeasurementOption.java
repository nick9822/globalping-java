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

/**
 * Represents a measurement option used in API requests, providing methods to convert the option
 * into JSON format.
 *
 * <p>Classes implementing this interface must define how their data is serialized
 * to both string-based and byte-based JSON formats.</p>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * DnsOptions dno = new DnsOptionsBuilder().withQuery(DnsQueryType.MX).build();
 * String jsonString = dnsOptions.toJson();
 * byte[] jsonBytes = dnsOptions.toJsonBytes();
 * }</pre>
 */
public interface MeasurementOption {

  /**
   * Converts the measurement option to its JSON string representation.
   *
   * @return a JSON string representing the measurement option.
   */
  String toJson();

  /**
   * Converts the measurement option to a JSON byte array.
   *
   * @return a byte array containing the JSON representation of the measurement option.
   */
  byte[] toJsonBytes();
}

/**
 * This class represents a generic measurement option. It holds value as an {@link JsonElement}
 * which can then retrieved to it's exact type of {@link Class} which implements
 * {@link MeasurementOption} by calling {@link #retrieveMeasurementOption(Class)} method.
 */
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

  /**
   * This generic method used to retrieve measurement option as an expected class of type
   * parameter.
   *
   * @param <T> the type of the response, which must implements {@link MeasurementOption}.
   * @param cl  the {@link Class} object representing the type of measurement option expected.
   * @return an instance of the measure option of type {@code T}.
   */
  public <T> T retrieveMeasurementOption(Class<T> cl) {
    return CustomGson.get().fromJson(this.value, cl);
  }
}

/**
 * Gson custom serializer for {@link MeasurementOption}.
 */
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