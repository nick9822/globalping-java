package model;

import java.util.Date;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TlsCertificate {

  Boolean authorized;
  String error;
  Date createdAt;
  Date expiresAt;
  TlsCertificateSubject subject;
  TlsCertificateIssuer issuer;
  String keyType;
  Integer keyBits;
  String serialNumber;
  String fingerprint256;
  String publicKey;
}
