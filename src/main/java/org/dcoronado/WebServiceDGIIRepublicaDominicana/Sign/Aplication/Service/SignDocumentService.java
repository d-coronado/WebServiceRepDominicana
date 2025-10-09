package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Service;


import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.In.SignDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.Out.CertificateLoaderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.Out.CertificateValidatorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.Out.CryptographicSignerPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Domain.KeyAndCertificate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class SignDocumentService implements SignDocumentUseCase {

    private final CertificateLoaderPort certificateLoaderPort;
    private final CertificateValidatorPort certificateValidatorPort;
    private final CryptographicSignerPort cryptographicSignerPort;

    public String execute(String documentContent, String certificateIdentifier, String password) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {

        KeyAndCertificate certificate = certificateLoaderPort.loadCertificate(certificateIdentifier, password);

        if (!certificateValidatorPort.isValidCertificate(certificate))
            throw new InvalidArgumentException("Certificate is not valid");

        return cryptographicSignerPort.signDocument(documentContent, certificate);

    }

}
