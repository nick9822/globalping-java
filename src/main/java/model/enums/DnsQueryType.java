package model.enums;

/**
 * Enum representing various DNS query types like {@code A}, {@code MX}, {@code TXT} etc. supported
 * by Globalping service.
 */
public enum DnsQueryType {
  A,
  AAAA,
  ANY,
  CNAME,
  DNSKEY,
  DS,
  HTTPS,
  MX,
  NS,
  NSEC,
  PTR,
  RRSIG,
  SOA,
  TXT,
  SRV
}
