package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Infraestructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.In.SignDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.SignProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignDocumentProviderAdapter implements SignProvider {

    private final SignDocumentUseCase signDocumentUseCase;

    @Override
    public String execute(String document, String pathCertificado, String claveCertificado) throws Exception {
        return signDocumentUseCase.execute(document, pathCertificado, claveCertificado);
    }

    @Override
    public String executeHash(String signedXml) throws Exception {
        return signDocumentUseCase.extractHash(signedXml);
    }
}
