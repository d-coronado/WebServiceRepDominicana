package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface SignDocumentProviderPort {
    String execute(String document,String pathCertificado,String claveCertificado) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;
}
