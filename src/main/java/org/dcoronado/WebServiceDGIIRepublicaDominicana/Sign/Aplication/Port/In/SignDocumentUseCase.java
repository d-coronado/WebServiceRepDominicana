package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.In;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface SignDocumentUseCase {
    String execute(String documentContent, String certificateIdentifier, String password) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException;
}
