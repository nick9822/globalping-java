package model;

import com.google.gson.annotations.JsonAdapter;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;
import model.enums.CustomGson;
import model.enums.MeasurementType;

@ToString
@Getter
public class MeasurementRequest {

  MeasurementType type;
  MeasurementTarget target;
  boolean inProgressUpdates;
  MeasurementLocations locations;
  Integer limit;

  @JsonAdapter(MeasurementOptionSerializer.class)
  MeasurementOption measurementOptions;

  private MeasurementRequest(MeasurementRequestBuilder builder) throws PayloadException {
    type = builder.type;
    target = builder.target;
    inProgressUpdates = builder.inProgressUpdates;
    locations = builder.locations;
    limit = builder.limit;
    measurementOptions = builder.measurementOptions;
    if (measurementOptions == null) {
      throw new PayloadException("measurement options are required");
    }

    switch (builder.type) {
      case ping:
        if (measurementOptions.getClass() != PingOptions.class) {
          throw new PayloadException("measurementOptions must be PingOptions");
        }
        break;
      case traceroute:
        if (measurementOptions.getClass() != TracerouteOptions.class) {
          throw new PayloadException("measurementOptions must be TraceRouteOptions");
        }
        break;
      case dns:
        if (measurementOptions.getClass() != DnsOptions.class) {
          throw new PayloadException("measurementOptions must be DnsOptions");
        }
        break;
      case mtr:
        if (measurementOptions.getClass() != MtrOptions.class) {
          throw new PayloadException("measurementOptions must be MtrOptions");
        }
        break;
      case http:
        if (measurementOptions.getClass() != HttpOptions.class) {
          throw new PayloadException("measurementOptions must be HttpOptions");
        }
        break;
      default:
        throw new PayloadException("invalid measurementOptions");
    }
  }


  public MeasurementOption getMeasurementOptions() {
    if (Objects.requireNonNull(type) == MeasurementType.ping) {
      return ((MeasurementOptionGeneric) measurementOptions).retrieveMeasurementOption(
          PingOptions.class);
    }
    return measurementOptions;
  }

  public byte[] toJsonBytes() {
    return CustomGson.get().toJson(this).getBytes(StandardCharsets.UTF_8);
  }

  public static class MeasurementRequestBuilder {

    MeasurementType type;
    MeasurementTarget target;


    boolean inProgressUpdates = false;
    MeasurementLocations locations;
    Integer limit;

    MeasurementOption measurementOptions;

    public MeasurementRequestBuilder(MeasurementType type, MeasurementTarget target) {
      this.type = type;
      this.target = target;
    }

    public MeasurementRequestBuilder withInProgressUpdates(boolean inProgressUpdates) {
      this.inProgressUpdates = inProgressUpdates;
      return this;
    }

    public MeasurementRequestBuilder withLocations(MeasurementLocations locations) {
      this.locations = locations;
      return this;
    }

    public MeasurementRequestBuilder withLimit(int limit) {
      this.limit = limit;
      return this;
    }

    public MeasurementRequestBuilder withMeasurementOptions(MeasurementOption measurementOptions) {
      this.measurementOptions = measurementOptions;
      return this;
    }

    public MeasurementRequest build() throws PayloadException {
      return new MeasurementRequest(this);
    }
  }
}