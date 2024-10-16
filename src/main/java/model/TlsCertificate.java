package model;

import java.util.Date;
import lombok.Getter;
import lombok.ToString;


/**
 * This class represents a TLS certificate.
 */
@ToString
@Getter
public class TlsCertificate {

  /**
   * Indicates whether a trusted authority signed the certificate.
   */
  Boolean authorized;
  /**
   * The reason for rejecting the certificate if {@code authorized} is {@code false}.
   */
  String error;
  /**
   * The creation date and time of the certificate.
   */
  Date createdAt;
  /**
   * The expiration date and time of the certificate.
   */
  Date expiresAt;
  TlsCertificateSubject subject;
  TlsCertificateIssuer issuer;
  /**
   * The type of the used key, or {@code null} for unrecognized types.
   */
  String keyType;
  /**
   * The size of the used key, or `null` for unrecognized types.
   */
  Integer keyBits;
  /**
   * The certificate serial number as a : separated HEX string.
   */
  String serialNumber;
  /**
   * The SHA-256 digest of the DER-encoded certificate as a : separated HEX string.
   */
  String fingerprint256;
  /**
   * The public key as a : separated HEX string, or `null` for unrecognized types.
   */
  String publicKey;
}
