package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.Out;


import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Domain.KeyAndCertificate;

public interface CryptographicSignerPort {
    String signDocument(String documentContent, KeyAndCertificate keyAndCertificate);
    String extractHash(String signedXml);
}
