package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.Out;


import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Domain.KeyAndCertificate;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;

public interface CertificateValidatorPort {
    boolean isValidCertificate(KeyAndCertificate keyAndCertificate) throws CertificateNotYetValidException, CertificateExpiredException;
}
