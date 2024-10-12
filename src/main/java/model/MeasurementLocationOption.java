package model;

import error.PayloadException;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import model.enums.ContinentCode;
import model.enums.RegionName;

@ToString
public class MeasurementLocationOption {

  ContinentCode continent;
  RegionName region;
  String country;
  String state;
  String city;
  Integer asn;
  String network;
  List<String> tags;
  String magic;
  Integer limit;

  public void setLimit(Integer limit) throws PayloadException {
    if (limit < 1 || limit > 200) {
      throw new PayloadException("limit should be within the range of 1 to 200");
    }
    this.limit = limit;
  }

  public static MeasurementLocationOption withContinent(ContinentCode continent) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.continent = continent;
    return mlo;
  }

  public static MeasurementLocationOption withRegion(RegionName region) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.region = region;
    return mlo;
  }

  public static MeasurementLocationOption withCountry(String country) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.country = country;
    return mlo;
  }

  public static MeasurementLocationOption withState(String state) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.state = state;
    return mlo;
  }

  public static MeasurementLocationOption withCity(String city) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.city = city;
    return mlo;
  }

  public static MeasurementLocationOption withAsn(Integer asn) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.asn = asn;
    return mlo;
  }

  public static MeasurementLocationOption withNetwork(String network) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.network = network;
    return mlo;
  }

  public static MeasurementLocationOption withTags(List<String> tags) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.tags = tags;
    return mlo;
  }

  public static MeasurementLocationOption withMagic(String magic) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.magic = magic;
    return mlo;
  }
}
