package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

public interface SignProviderPort {
    String execute(String document, String pathCertificado, String claveCertificado) throws Exception;
}
