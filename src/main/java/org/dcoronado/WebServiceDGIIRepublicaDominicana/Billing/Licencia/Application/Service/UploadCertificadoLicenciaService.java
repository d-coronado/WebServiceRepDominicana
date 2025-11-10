package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.UploadCertificadoDigitalCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UploadCertificadoUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Model.DocumentFile;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.SaveFilePort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.RutasDirectoriosLicencia.getRutaCertificadoDigital;


/**
 * Servicio de aplicación encargado de cargar y registrar
 * un certificado digital asociado a una licencia.
 * Se encarga de validar los datos de entrada, verificar la existencia de la licencia,
 * guardar el archivo del certificado en la ruta correspondiente
 * y actualizar la información de la licencia con los datos del certificado.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadCertificadoLicenciaService implements UploadCertificadoUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final SaveFilePort saveFilePort;


    /**
     * Carga un certificado digital y lo asocia a la licencia correspondiente.
     * @param command Objeto que contiene los datos necesarios para cargar el certificado digital.
     * @throws IOException       si ocurre un error al guardar el archivo
     * @throws NotFoundException si no se encuentra la licencia con el RNC indicado
     */
    @Override
    public void execute(UploadCertificadoDigitalCommand command) throws IOException {
        log.info("INICIO - Proceso de carga de certificado digital para RNC: {}", command.rnc());

        log.info("[1] Validando datos de entrada");
        RNC rncValue = RNC.of(command.rnc());
        DocumentFile certificadoFile = DocumentFile.of(command.nombreCertificado(), command.certificadoDigitalContenido());

        // Buscar licencia y validar existencia
        log.info("[2] Buscando licencia existente con RNC {}", rncValue);
        Licencia licenciaSaved = licenciaRepositoryPort.findByRnc(rncValue.getValor())
                .orElseThrow(() -> new NotFoundException("Licencia con RNC " + rncValue.getValor() + " no encontrada"));

        log.info("[3] Preparando certificado digital");
        licenciaSaved.prepararCertificadoDigital(certificadoFile, command.claveCertificado());

        log.info("[4] Armando ruta completa para guardar el certificado digital");
        final String rutaRelativaCertificado = String.join("/", getRutaCertificadoDigital(licenciaSaved.getRnc().getValor()),licenciaSaved.getCertificadoDigital().getCertificado().getNombre());
        final String rutaBase = saveFilePort.getBasePath();
        final String rutaCompletaCertificado = rutaBase.concat(rutaRelativaCertificado);
        licenciaSaved.confirmarCertificadoDigital(rutaCompletaCertificado);

        log.info("[5] Guardando archivo del certificado en disco");
        saveFilePort.save(licenciaSaved.getCertificadoDigital().getRutaCertificado(), command.certificadoDigitalContenido());

        log.info("[6] Guardando cambios en el repositorio de licencia.");
        licenciaRepositoryPort.save(licenciaSaved);
    }
}
