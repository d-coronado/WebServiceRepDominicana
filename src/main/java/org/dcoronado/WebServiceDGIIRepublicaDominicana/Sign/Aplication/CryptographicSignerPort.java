package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication;


import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Domain.KeyAndCertificate;

public interface CryptographicSignerPort {
    String signDocument(String documentContent, KeyAndCertificate keyAndCertificate);
}
