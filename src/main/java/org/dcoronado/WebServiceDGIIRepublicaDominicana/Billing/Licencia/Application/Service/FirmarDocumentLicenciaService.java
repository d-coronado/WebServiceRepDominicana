package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.FirmarDocumentUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignProvider;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.validateExtensionXml;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.validateArchivo;


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
     * @param rnc             identificador único de la licencia
     * @param nombreDocumento nombre del archivo a firmar
     * @param archivo         contenido binario del archivo
     * @return documento firmado en formato texto
     * @throws Exception         si ocurre un error durante el proceso de firma
     * @throws NotFoundException si no se encuentra la licencia con el RNC indicado
     */
    @Override
    public String firmarDocumentByLicencia(final String rnc,final String nombreDocumento, byte[] archivo) throws Exception {


        log.info("INICIO - Proceso de firma de documento para licencia con RNC {}",rnc);

        // Validar parámetros de entrada
        log.info("[1] Validando datos de entrada");
        notBlank(rnc, "RNC required");
        validateArchivo(nombreDocumento, archivo);
        validateExtensionXml(nombreDocumento);

        // Buscar licencia y validar existencia
        log.info("[2] Buscando licencia por RNC {}", rnc);
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new NotFoundException("Licencia con rnc: " + rnc + " not found"));

        // Validar que la licencia tiene datos suficientes para firmar
        log.info("[3] Buscando que la licencia tiene datos necesarios para firma");
        licencia.validarDatosParaFirma();

        // Convertir el archivo a String
        log.info("[4] Convirtiendo archivo a String");
        String documentoString = new String(archivo, StandardCharsets.UTF_8);

        // Delegar la firma al proveedor externo
        log.info("[5] Firmando documento");
        return signProviderPort.execute(documentoString, licencia.getRutaCertificado(), licencia.getClaveCertificado());
    }
}
