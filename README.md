# Globalping Java client library

[![Maven Central](https://img.shields.io/badge/maven--central-v1.0.0-blue)](https://mvnrepository.com/artifact/in.arvx/globalping-java)
[![javadoc](https://javadoc.io/badge2/in.arvx/globalping-java/javadoc.svg)](https://javadoc.io/doc/in.arvx/globalping-java)
[![CodeQL](https://github.com/nick9822/globalping-java/actions/workflows/codeql.yml/badge.svg?branch=main)](https://github.com/nick9822/globalping-java/actions/workflows/codeql.yml) 
[![Coverage Status](https://coveralls.io/repos/github/nick9822/globalping-java/badge.svg?branch=main)](https://coveralls.io/github/nick9822/globalping-java?branch=main)

## Installation

### Requirements

- Java 1.8 or later

### Gradle users
Add this dependency to your project's build file:
```
implementation "in.arvx:globalping-java:1.0.0"
```

### Maven users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>in.arvx</groupId>
  <artifactId>globalping-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

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

## Async Usage
```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new PingOptionsBuilder().withPackets(3).withIpVersion(4).build())
    .build();

gpclient.requestAndPollMeasurementAsync(measurementRequest).thenAccept(res -> {
      System.out.println(res.getPingTestResults());
    }).exceptionally(e -> {
      Assertions.fail("Something went wrong: " + e.getMessage());
      return null;
    });
```

## Examples

- [Ping measurement](#ping-measurement-with-8-packets-north-america--japan)
- [Traceroute measurement](#traceroute-measurement-north-america--japan)
- [Dns measurement (Trace)](#dns-measurement-mx--trace-enabled-north-america--japan)
- [Dns measurement](#dns-measurement-mx--trace-disabled-north-america--japan)
- [Mtr measurement](#mtr-measurement-north-america--japan)
- [Http measurement](#http-measurement-north-america--japan)
- [Probes](#probes)
- [Limits](#limits)

### Ping measurement with 8 packets (North America & Japan)

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.ping,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new PingOptionsBuilder().withPackets(8).build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedPingTestResult> fgp = res1.getPingTestResults();
```

### Traceroute measurement (North America & Japan)

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.traceroute,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new TracerouteOptionsBuilder().build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedTracerouteTestResult> fgp = res1.getTracerouteTestResults();
```

### Dns measurement (MX & Trace enabled) (North America & Japan)

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.dns,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new DnsOptionsBuilder()
        .withQuery(DnsQueryType.MX)
        .withTrace(true).build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedTraceDnsTestResult> fgp = res1.getDnsTestResults();
```

### Dns measurement (MX & Trace disabled) (North America & Japan)

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.dns,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new DnsOptionsBuilder().withQuery(DnsQueryType.MX).build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedSimpleDnsTestResult> fgp = res1.getDnsTestResults();
```

### Mtr measurement (North America & Japan)

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.mtr,
    new MeasurementTarget(TargetType.HostName, "cdn.jsdelivr.net"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new MtrOptionsBuilder().build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedMtrTestResult> fgp = res1.getMtrTestResults();
```

### Http measurement (North America & Japan)

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");

MeasurementHttpRequest mhr = new MeasurementHttpRequest();
mhr.setMethod(HttpMethod.GET);

MeasurementRequest measurementRequest = new MeasurementRequestBuilder(MeasurementType.http,
    new MeasurementTarget(TargetType.HostName, "www.google.com"))
    .withLocations(new MeasurementLocations(
        Arrays.asList(MeasurementLocationOption.withRegion(RegionName.NORTHERN_AMERICA),
            MeasurementLocationOption.withCountry("JP"))))
    .withMeasurementOptions(new HttpOptionsBuilder()
        .withRequest(mhr).build())
    .build();

CreateMeasurementResponse res = gpclient.requestMeasurement(measurementRequest);

// wait for 500ms
Thread.sleep(500);

MeasurementResponse res1 = gpclient.pollForMeasurement(res.getId());
List<FinishedHttpTestResult> fgp = res1.getHttpTestResults();
```

### Probes

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");
Probes res = gpclient.getProbes();
```

### Limits

```java
GlobalpingClient gpclient = GlobalpingClient.init("https://api.globalping.io", "");
Probes res = gpclient.getLimits();
```
