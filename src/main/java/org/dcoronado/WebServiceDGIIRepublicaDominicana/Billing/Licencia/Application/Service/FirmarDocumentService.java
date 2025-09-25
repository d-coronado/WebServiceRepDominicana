package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.FirmarDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.SignProviderPort;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Service
@RequiredArgsConstructor
public class FirmarDocumentService implements FirmarDocumentUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final SignProviderPort signProviderPort;

    @Override
    public String firmarDocumentByLicencia(String rnc, InputStream archivo) throws Exception {
        notBlank(rnc,"Rnc required");
        byte[] archivoBytes = archivo.readAllBytes();
        if (archivoBytes.length == 0) throw new InvalidArgumentException ("Archivo no puede estar vacío");
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new NotFoundException("Licencia con rnc: " + rnc + " not found"));
        licencia.validarDatosParaFirma();
        String documentoString = new String(archivo.readAllBytes(), StandardCharsets.UTF_8);
        return signProviderPort.execute(documentoString,licencia.getRutaCertificado(),licencia.getClaveCertificado());
    }
}
