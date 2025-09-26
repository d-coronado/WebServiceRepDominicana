package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.FirmarDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Validator.ArchivoValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignProviderPort;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Service
@RequiredArgsConstructor
public class FirmarDocumentLicenciaService implements FirmarDocumentUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final SignProviderPort signProviderPort;

    @Override
    public String firmarDocumentByLicencia(String rnc, String nombreDocumento, byte[] archivo) throws Exception{
        notBlank(rnc,"RNC required");
        ArchivoValidator.validate(nombreDocumento,archivo);
        ArchivoValidator.validateExtensionXml(nombreDocumento);
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new NotFoundException("Licencia con rnc: " + rnc + " not found"));
        licencia.validarDatosParaFirma();
        String documentoString = new String(archivo, StandardCharsets.UTF_8);
        return signProviderPort.execute(documentoString,licencia.getRutaCertificado(),licencia.getClaveCertificado());
    }
}
