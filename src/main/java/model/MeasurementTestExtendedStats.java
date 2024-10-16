package model;

/**
 * This class extends stats of measurement results, it contains additional fields along with the
 * extended {@link MeasurementTestStats}.
 */
public class MeasurementTestExtendedStats extends MeasurementTestStats {

  /**
   * The standard deviation of the `rtt` values.
   */
  Double stDev;
  /**
   * The lowest jitter value.
   */
  Double jMin;
  /**
   * The average jitter value.
   */
  Double jAvg;
  /**
   * The highest jitter value.
   */
  Double jMax;
}
