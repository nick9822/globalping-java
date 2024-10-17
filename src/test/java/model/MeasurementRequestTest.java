package model;

import error.PayloadException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import model.DnsOptions.DnsOptionsBuilder;
import model.HttpOptions.HttpOptionsBuilder;
import model.MeasurementRequest.MeasurementRequestBuilder;
import model.MtrOptions.MtrOptionsBuilder;
import model.PingOptions.PingOptionsBuilder;
import model.TracerouteOptions.TracerouteOptionsBuilder;
import model.enums.MeasurementType;
import model.enums.RegionName;
import model.enums.TargetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MeasurementRequestTest {

  String expectedMrPingOptions = "{\"type\":\"ping\",\"target\":\"cdn.jsdelivr.net\",\"inProgressUpdates\":false,\"measurementOptions\":{\"packets\":3}}";
  String expectedMrPingOptionsInProgressUpdates = "{\"type\":\"ping\",\"target\":\"cdn.jsdelivr.net\",\"inProgressUpdates\":true,\"measurementOptions\":{\"packets\":3}}";
  String expectedMrPingOptionsMeasurementLocations = "{\"type\":\"ping\",\"target\":\"cdn.jsdelivr.net\",\"inProgressUpdates\":false,\"locations\":[{\"region\":\"Northern America\"},{\"country\":\"JP\"}],\"measurementOptions\":{\"packets\":3}}";
  String expectedMrPingOptionsLimit = "{\"type\":\"ping\",\"target\":\"cdn.jsdelivr.net\",\"inProgressUpdates\":false,\"limit\":500,\"measurementOptions\":{\"packets\":3}}";

  PingOptions pingOptions = new PingOptionsBuilder().build();
  TracerouteOptions tracerouteOptions = new TracerouteOptionsBuilder().build();
  DnsOptions dnsOptions = new DnsOptionsBuilder().build();
  MtrOptions mtrOptions = new MtrOptionsBuilder().build();
  HttpOptions httpOptions = new HttpOptionsBuilder().build();

  Map<MeasurementType, MeasurementOption> mTypeMap = new HashMap<MeasurementType, MeasurementOption>() {{
    put(MeasurementType.ping, pingOptions);
    put(MeasurementType.traceroute, tracerouteOptions);
    put(MeasurementType.dns, dnsOptions);
    put(MeasurementType.mtr, mtrOptions);
    put(MeasurementType.http, httpOptions);
  }};

  MeasurementOption[] allOptions = {pingOptions, tracerouteOptions, dnsOptions, mtrOptions,
      httpOptions};

  @Test
  @DisplayName("Measurement Request without measurement options")
  public void mr_without_measurement_options_test() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
          new MeasurementTarget(
              TargetType.HostName, "cdn.jsdelivr.net")).build();
    }, "measurement options are required");
  }

  @Test
  @DisplayName("Measurement Request with Measurement Options")
  public void mr_withMeasurementOptions_test() throws PayloadException {

    MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
        new MeasurementTarget(
            TargetType.HostName, "cdn.jsdelivr.net"))
        .withMeasurementOptions(pingOptions)
        .build();
    Assertions.assertEquals(expectedMrPingOptions, mr.toJson());
  }

  @Test
  @DisplayName("Measurement Request with InProgressUpdates=true")
  public void mr_withInProgressUpdates_test() throws PayloadException {

    MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
        new MeasurementTarget(
            TargetType.HostName, "cdn.jsdelivr.net"))
        .withMeasurementOptions(pingOptions)
        .withInProgressUpdates(true)
        .build();

    Assertions.assertEquals(expectedMrPingOptionsInProgressUpdates, mr.toJson());
  }

  @Test
  @DisplayName("Measurement Request with Measurement locations")
  public void mr_withMeasurementLocations_test() throws PayloadException {

    MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
        new MeasurementTarget(
            TargetType.HostName, "cdn.jsdelivr.net"))
        .withMeasurementOptions(pingOptions)
        .withLocations(new MeasurementLocations(
            Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
                MeasurementLocationOption.withCountry("JP"))))
        .build();

    Assertions.assertEquals(expectedMrPingOptionsMeasurementLocations, mr.toJson());
  }

  @Test
  @DisplayName("Measurement Request with limit")
  public void mr_withLimit_test() throws PayloadException {

    MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
        new MeasurementTarget(
            TargetType.HostName, "cdn.jsdelivr.net"))
        .withMeasurementOptions(pingOptions)
        .withLimit(500)
        .build();

    Assertions.assertEquals(expectedMrPingOptionsLimit, mr.toJson());
  }

  @Test
  @DisplayName("Measurement Request with mismatched measurement options")
  public void mr_withMismatched_measurement_options_test() throws PayloadException {

    for (MeasurementType mtype : MeasurementType.values()) {
      for (MeasurementOption e : allOptions) {
        if (mTypeMap.get(mtype) == e) {
          continue;
        }

        Assertions.assertThrowsExactly(PayloadException.class, () -> {
          MeasurementRequest mr = new MeasurementRequestBuilder(mtype,
              new MeasurementTarget(
                  TargetType.HostName, "cdn.jsdelivr.net"))
              .withMeasurementOptions(e)
              .build();
        }, "expected a mismatch options throw " + mtype + " " + e);
      }
    }
  }

  @Test
  @DisplayName("Measurement Request with wrong limit")
  public void mr_withWrongLimit() {
    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
          new MeasurementTarget(
              TargetType.HostName, "cdn.jsdelivr.net"))
          .withMeasurementOptions(pingOptions)
          .withLimit(1000)
          .build();
    });
  }

  @Test
  @DisplayName("Measurement Request with limit and with Measurement locations with their own limit")
  public void mr_withLimitAndLocationWithLimit_test() {

    Assertions.assertThrowsExactly(PayloadException.class, () -> {
      MeasurementLocationOption mlo = MeasurementLocationOption.withRegion(
          RegionName.NORTHERN_AMERICA);
      mlo.setLimit(10);

      MeasurementRequest mr = new MeasurementRequestBuilder(MeasurementType.ping,
          new MeasurementTarget(
              TargetType.HostName, "cdn.jsdelivr.net"))
          .withMeasurementOptions(pingOptions)
          .withLocations(new MeasurementLocations(
              Arrays.asList(mlo)))
          .withLimit(100)
          .build();
    });
  }

}