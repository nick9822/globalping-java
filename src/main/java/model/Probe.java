package model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Probe {

  String version;
  ProbeLocation location;
  List<String> tags;
  List<String> resolvers;
}
