package model;

import com.google.gson.annotations.JsonAdapter;

import error.PayloadException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;
import model.enums.ContinentCode;
import model.enums.CustomGson;
import model.enums.MeasurementType;

/**
 * This class represents request payload for the measurement. It provides a builder to constructor
 * the payload safely.
 * <p>
 * Use {@link MeasurementRequestBuilder} to build the Measurement Request.
 */
@ToString
@Getter
public class MeasurementRequest {

  MeasurementType type;
  MeasurementTarget target;
  /**
   * Indicates whether you want to get partial results while the measurement is still running:
   * <ul>
   *   <li>If {@code true}, partial results are returned as soon as they are available, and you can
   *   present them to the user in real time. Note that only the first 5 tests from the {@code results}
   *   array will update in real time.</li>
   *   <li>If {@code false}, the result of each test is updated only after the test finishes.</li>
   * </ul>
   * defaults to false
   */
  boolean inProgressUpdates;
  MeasurementLocations locations;
  /**
   * The maximum number of probes that should run the measurement.
   * <p>The result count might be lower if there aren't enough probes available in the specified
   * locations.</p>
   * <p>Mutually exclusive with the `limit` property that can be set for individual locations.</p>
   */
  Integer limit;

  @JsonAdapter(MeasurementOptionSerializer.class)
  MeasurementOption measurementOptions;

  /**
   * Constructor which takes {@link MeasurementRequestBuilder} and creates a
   * {@link MeasurementRequest}
   *
   * @param builder {@link MeasurementRequestBuilder}
   * @throws PayloadException if not a valid payload
   */
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

  /**
   * This class represent a builder of Measurement Request.
   *
   * @see MeasurementRequestBuilder#MeasurementRequestBuilder(MeasurementType, MeasurementTarget)
   */
  public static class MeasurementRequestBuilder {

    MeasurementType type;
    MeasurementTarget target;
    boolean inProgressUpdates = false;
    MeasurementLocations locations;
    Integer limit;

    MeasurementOption measurementOptions;

    /**
     * Constructor of Builder {@link MeasurementRequest}
     *
     * @param type   {@link MeasurementType}
     * @param target {@link MeasurementTarget}
     *               <h3>Example Usage:</h3>
     *               <pre>{@code
     *                 MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
     *                       new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
     *                       .withLocations(new MeasurementLocations(
     *                           Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
     *                               MeasurementLocationOption.withCountry("JP"))))
     *                       .withMeasurementOptions(new PingOptionsBuilder().withPackets(3).withIpVersion(4).build())
     *                       .build();
     *               }
     *               </pre>
     */
    public MeasurementRequestBuilder(MeasurementType type, MeasurementTarget target) {
      this.type = type;
      this.target = target;
    }

    /**
     * Chain method for setting value of inProgressUpdates
     *
     * @param inProgressUpdates {@link MeasurementRequest#inProgressUpdates}
     * @return {@link MeasurementRequestBuilder}
     */
    public MeasurementRequestBuilder withInProgressUpdates(boolean inProgressUpdates) {
      this.inProgressUpdates = inProgressUpdates;
      return this;
    }

    /**
     * Chain method for setting value of locations
     *
     * @param locations {@link MeasurementLocations}
     * @return {@link MeasurementRequestBuilder}
     */
    public MeasurementRequestBuilder withLocations(MeasurementLocations locations) {
      this.locations = locations;
      return this;
    }

    /**
     * Chain method for setting value of limit
     *
     * @param limit {@link MeasurementRequest#limit}
     * @return {@link MeasurementRequestBuilder}
     */
    public MeasurementRequestBuilder withLimit(int limit) {
      this.limit = limit;
      return this;
    }

    /**
     * Chain method for setting value of measurementOptions
     *
     * @param measurementOptions {@link MeasurementOption}
     * @return {@link MeasurementRequestBuilder}
     */
    public MeasurementRequestBuilder withMeasurementOptions(MeasurementOption measurementOptions) {
      this.measurementOptions = measurementOptions;
      return this;
    }

    /**
     * Final method in the chain to build a {@link MeasurementRequest}
     *
     * @return {@link MeasurementRequest}
     */
    public MeasurementRequest build() throws PayloadException {
      return new MeasurementRequest(this);
    }
  }
}