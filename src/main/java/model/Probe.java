package model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents a Globalping Probe and it's properties.
 */
@Getter
@ToString
public class Probe {

  /**
   * The probe version.
   */
  String version;
  ProbeLocation location;
  /**
   * An array of additional values to fine-tune probe selection.
   * <ul>
   *   <li>Probes hosted in <a href="https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regi
   *   ons-availability-zones.html#concepts-available-regions">AWS</a>)
   *   and <a href="https://cloud.google.com/compute/docs/regions-zones#available">Google Cloud</a>
   *   are automatically assigned the service region code.
   *   For example: {@code aws-eu-west-1} and {@code gcp-us-south1}.</li>
   *   <li>Probes are automatically assigned {@code datacenter-network} and {@code eyeball-network}
   *   tags to distinguish between datacenter and end-user locations.</li>
   * </ul>
   */
  List<String> tags;
  /**
   * An array of the default resolvers configured on the probe.
   */
  List<String> resolvers;
}
