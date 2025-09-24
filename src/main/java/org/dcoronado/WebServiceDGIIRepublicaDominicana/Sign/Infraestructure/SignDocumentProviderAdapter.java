package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Infraestructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.SignDocumentProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.SignDocumentUseCase;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@Component
@RequiredArgsConstructor
public class SignDocumentProviderAdapter implements SignDocumentProviderPort {

    private final SignDocumentUseCase signDocumentUseCase;

    @Override
    public String execute(String document, String pathCertificado, String claveCertificado) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        return signDocumentUseCase.execute(document,pathCertificado,claveCertificado);
    }
}
