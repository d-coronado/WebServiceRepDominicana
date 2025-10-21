package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

public interface SignProviderPort {
    String execute(String document, String pathCertificado, String claveCertificado) throws Exception;
    String executeHash(String signedXml) throws Exception;
}
