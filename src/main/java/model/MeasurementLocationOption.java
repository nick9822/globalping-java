package model;

import error.PayloadException;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import model.enums.ContinentCode;
import model.enums.RegionName;

/**
 * This class represents various measurement location options, except limit the others can be
 * present one at a time.
 * <p>For example: in a single MeasurementLocationOption you will not find continent and region
 * both values given.</p>
 *
 * <p>Use static {@code with******} methods to construct measurement location options.</p>
 * <p>Use {@link #setLimit(Integer)} to change the limit.</p>
 */
@ToString
public class MeasurementLocationOption {

  ContinentCode continent;
  RegionName region;

  /**
   * A two-letter country code based on <a
   * href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements">ISO
   * 3166-1 alpha-2</a>
   */
  String country;

  /**
   * A two-letter <a
   * href="https://www.faa.gov/air_traffic/publications/atpubs/cnt_html/appendix_a.html">US state
   * code</a>.
   */
  String state;

  /**
   * A city name in English.
   */
  String city;

  /**
   * An autonomous system number (ASN).
   */
  Integer asn;

  /**
   * A network name, such as "Google LLC" or "DigitalOcean, LLC".
   */
  String network;

  /**
   * An array of additional values to fine-tune probe selection
   * <ul>
   *   <li>Probes hosted in <a href="https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-available-regions">AWS</a>)
   *   and <a href="https://cloud.google.com/compute/docs/regions-zones#available">Google Cloud</a> are automatically assigned the service region code.
   *   For example: {@code aws-eu-west-1} and {@code gcp-us-south1}.</li>
   *   <li>Probes are automatically assigned {@code datacenter-network} and {@code eyeball-network} tags to
   *   distinguish between datacenter and end-user locations.</li>
   * </ul>
   */
  List<String> tags;

  /**
   * Locations defined in a single string instead of the respective location properties.
   * <p>The API performs fuzzy matching on the {@code country}, {@code city}, {@code state},
   * {@code continent}, {@code region},
   * {@code asn} (using {@code AS} prefix, e.g., {@code AS123}), {@code tags}, and {@code network}
   * values.</p>
   * <p>Supports full names, ISO codes (where applicable), and common aliases.
   * Multiple conditions can be combined using the {@code +} character.</p>
   */
  String magic;

  /**
   * The maximum number of probes that should run the measurement in this location.
   * <p>The result count might be lower if there aren't enough probes available in this
   * location.</p>
   * <p>Mutually exclusive with the global `limit` property.</p>
   * <p> {@code 1 <= limit >= 200} and defaults to 1</p>
   */
  Integer limit;

  /**
   * Sets the limit
   *
   * @param limit limit to change to must be within the rage of 1 to 200.
   * @throws PayloadException if the given limit is off the range
   */
  public void setLimit(Integer limit) throws PayloadException {
    if (limit < 1 || limit > 200) {
      throw new PayloadException("limit should be within the range of 1 to 200");
    }
    this.limit = limit;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only continent and default limit
   *
   * @param continent valid {@link ContinentCode}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withContinent(ContinentCode continent) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.continent = continent;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only region and default limit
   *
   * @param region valid {@link RegionName}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withRegion(RegionName region) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.region = region;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only country and default limit
   *
   * @param country {@link #country}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withCountry(String country) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.country = country;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only state and default limit
   *
   * @param state {@link #state}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withState(String state) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.state = state;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only city and default limit
   *
   * @param city {@link #city}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withCity(String city) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.city = city;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only asn and default limit
   *
   * @param asn {@link #asn}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withAsn(Integer asn) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.asn = asn;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only network and default limit
   *
   * @param network {@link #network}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withNetwork(String network) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.network = network;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only tags and default limit
   *
   * @param tags {@link #tags}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withTags(List<String> tags) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.tags = tags;
    return mlo;
  }

  /**
   * Constructs {@link MeasurementLocationOption} with only magic and default limit
   *
   * @param magic {@link #magic}
   * @return {@link MeasurementLocationOption}
   */
  public static MeasurementLocationOption withMagic(String magic) {
    MeasurementLocationOption mlo = new MeasurementLocationOption();
    mlo.magic = magic;
    return mlo;
  }
}
