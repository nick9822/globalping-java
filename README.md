# Globalping Java client library

## Installation

### Requirements

- Java 1.8 or later

## Usage
```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new PingOptionsBuilder().withPackets(3).withIpVersion(4).build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedPingTestResult> fgp = res1.getPingTestResults();
```