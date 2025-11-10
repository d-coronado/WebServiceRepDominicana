package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.FirmarDocumentoCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.FirmarDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.SignProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.DocumentFile;
import org.springframework.stereotype.Service;

/**
 * Servicio de aplicación encargado de firmar documentos electrónicos asociados a una licencia.
 * Se encarga de validar el RNC y el archivo recibido, verificar la existencia de la licencia,
 * comprobar que la licencia tenga los datos necesarios para la firma,
 * y delegar la firma al proveedor externo.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FirmarDocumentLicenciaService implements FirmarDocumentUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final SignProvider signProviderPort;

    /**
     * Firma un documento electrónico asociado a una licencia.
     *
     * @param command objeto que contiene los datos necesarios para firmar el documento
     * @return documento firmado en formato texto
     * @throws Exception         si ocurre un error durante el proceso de firma
     * @throws NotFoundException si no se encuentra la licencia con el RNC indicado
     */
    @Override
    public String firmarDocumentByLicencia(FirmarDocumentoCommand command) throws Exception {

        log.info("INICIO - Proceso de firma de documento para licencia con RNC {}", command.rnc());

        // Validar parámetros de entrada
        log.info("[1] Validando datos de entrada");
        RNC rncValue = RNC.of(command.rnc());

        DocumentFile file = DocumentFile.of(command.nombreDocumento(), command.documento());
        file.validateExtension("xml");

        // Buscar licencia y validar existencia
        log.info("[2] Buscando licencia por RNC {}", rncValue);
        Licencia licenciaSaved = licenciaRepositoryPort.findByRnc(rncValue.getValor())
                .orElseThrow(() -> new NotFoundException("Licencia con rnc: " + rncValue + " not found"));

        // Validar que la licencia tiene datos suficientes para firmar
        log.info("[3] Buscando que la licencia tiene datos necesarios para firma");
        licenciaSaved.puedeRealizarFirmaDocumento();

        // Convertir el archivo a String
        log.info("[4] Convirtiendo archivo a String");
        String documentoString = file.asText();

        // Delegar la firma al proveedor externo
        log.info("[5] Firmando documento");
        return signProviderPort.execute(documentoString, licenciaSaved.getCertificadoDigital().getRutaCertificado(), licenciaSaved.getCertificadoDigital().getClave());
    }
}
