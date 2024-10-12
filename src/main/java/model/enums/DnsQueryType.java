package model.enums;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

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
