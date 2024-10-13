package model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import error.PayloadException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.enums.TargetType;

/**
 * This class represents target of the measurement.
 * <p>A publicly reachable measurement target. Typically a hostname, an IPv4 address, or IPv6
 * address, depending on the measurement type.</p>
 * <p><i>Note: Support for IPv6 targets is currently considered experimental.</i></p>
 */
@JsonAdapter(MeasurementTargetTypeAdapter.class)
public class MeasurementTarget {

  String value;

  final Pattern IP4_PATTERN = Pattern.compile(
      "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$");
  final Pattern IP6_PATTERN = Pattern.compile(
      "^(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))$");
  final Pattern HOSTNAME_PATTERN = Pattern.compile(
      "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");

  Matcher matcher;

  MeasurementTarget(String text) {
    value = text;
  }

  /**
   * Constructor to safely creates instance of the class, pre-emptively checks if target is valid.
   *
   * @param ttype {@link TargetType} type of the target Hostname, IPv4, IPv6
   * @param text  {@link String} target value
   * @throws PayloadException when type does not match the given value
   */
  public MeasurementTarget(TargetType ttype, String text) throws PayloadException {
    switch (ttype) {
      case IPv4:
        matcher = IP4_PATTERN.matcher(text);
        if (!matcher.matches()) {
          throw new PayloadException("invalid ipv4 target");
        }
        break;
      case IPv6:
        matcher = IP6_PATTERN.matcher(text);
        if (!matcher.matches()) {
          throw new PayloadException("invalid ipv6 target");
        }
        break;
      case HostName:
        matcher = HOSTNAME_PATTERN.matcher(text);
        if (!matcher.matches()) {
          throw new PayloadException("invalid hostname target");
        }
        break;
      default:
        throw new PayloadException("invalid target name");
    }
    value = text;
  }
}

class MeasurementTargetTypeAdapter extends TypeAdapter<MeasurementTarget> {

  @Override
  public void write(JsonWriter jsonWriter, MeasurementTarget o) throws IOException {
    jsonWriter.value(o.value);
  }

  @Override
  public MeasurementTarget read(JsonReader jsonReader) throws IOException {
    return new MeasurementTarget(jsonReader.nextString());
  }
}
