package org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing.Domain;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class KeyAndCertificate {

    public final PrivateKey privateKey;
    public final X509Certificate certificate;

    public KeyAndCertificate(PrivateKey privateKey, X509Certificate certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }
}
