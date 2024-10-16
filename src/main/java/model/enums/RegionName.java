package model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum representing geographic region name based on UN <a href="https://unstats.un.org/unsd/
 * methodology/m49/">[Standard Country or Area Codes for
 * Statistical Use (M49)]</a>.
 */
public enum RegionName {
  @SerializedName("Northern Africa")
  NORTHERN_AFRICA,
  @SerializedName("Eastern Africa")
  EASTERN_AFRICA,
  @SerializedName("Middle Africa")
  MIDDLE_AFRICA,
  @SerializedName("Southern Africa")
  SOUTHERN_AFRICA,
  @SerializedName("Western Africa")
  WESTERN_AFRICA,
  @SerializedName("Caribbean")
  CARIBBEAN,
  @SerializedName("Central America")
  CENTRAL_AMERICA,
  @SerializedName("South America")
  SOUTH_AMERICA,
  @SerializedName("Northern America")
  NORTHERN_AMERICA,
  @SerializedName("Central Asia")
  CENTRAL_ASIA,
  @SerializedName("Eastern Asia")
  EASTERN_ASIA,
  @SerializedName("South-eastern Asia")
  SOUTH_EASTERN_ASIA,
  @SerializedName("Southern Asia")
  SOUTHERN_ASIA,
  @SerializedName("Western Asia")
  WESTERN_ASIA,
  @SerializedName("Eastern Europe")
  EASTERN_EUROPE,
  @SerializedName("Northern Europe")
  NORTHERN_EUROPE,
  @SerializedName("Southern Europe")
  SOUTHERN_EUROPE,
  @SerializedName("Western Europe")
  WESTERN_EUROPE,
  @SerializedName("Australia and New Zealand")
  AUSTRALIA_AND_NEW_ZEALAND,
  @SerializedName("Melanesia")
  MELANESIA,
  @SerializedName("Micronesia")
  MICRONESIA,
  @SerializedName("Polynesia")
  POLYNESIA
}
