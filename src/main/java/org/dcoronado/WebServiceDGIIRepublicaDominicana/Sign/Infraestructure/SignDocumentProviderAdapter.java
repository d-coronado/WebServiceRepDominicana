package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Infraestructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.In.SignDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignDocumentProviderAdapter implements SignPort {

    private final SignDocumentUseCase signDocumentUseCase;

    @Override
    public String execute(String document, String pathCertificado, String claveCertificado) throws Exception {
        return signDocumentUseCase.execute(document,pathCertificado,claveCertificado);
    }
}
