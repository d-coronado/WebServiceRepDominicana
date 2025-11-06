package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UploadCertificadoUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.SaveFilePort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.RutasDirectoriosLicencia.getRutaCertificadoDigital;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.validateArchivo;


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
    private final SaveFilePort saveArchivoLicenciaPort;


    /**
     * Carga un certificado digital y lo asocia a la licencia correspondiente.
     *
     * @param rnc           identificador único de la licencia
     * @param nombreArchivo nombre del archivo del certificado
     * @param archivo       contenido binario del certificado
     * @param password      contraseña del certificado
     * @throws IOException       si ocurre un error al guardar el archivo
     * @throws NotFoundException si no se encuentra la licencia con el RNC indicado
     */
    @Override
    public void execute(final String rnc, final String nombreArchivo, byte[] archivo, final String password) throws IOException {
        log.info("INICIO - Proceso de carga de certificado digital para RNC: {}", rnc);

        // Validar parámetros de entrada
        log.info("[1] Validando parámetros de entrada...");
        notBlank(rnc, "RNC required");
        notBlank(password, "Password required");
        validateArchivo(nombreArchivo, archivo);

        // Buscar licencia y validar existencia
        log.info("[2] Buscando licencia existente con RNC {}", rnc);
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new NotFoundException("Licencia con RNC " + rnc + " no encontrada"));


        // Si no tiene el directorio creado no se va a poder guardar el certificado
        log.info("[3] Verificando estado de setup de directorios...");
        if (!licencia.tieneSetupDirectoriosCompletado()) {
            throw new InvalidArgumentException(
                    "No se puede subir la licencia porque la creación de directorios aún está pendiente. " +
                            "Estado actual: %s".formatted(licencia.getDirectoriesSetupStatus())
            );
        }

        log.info("[4] Guardando archivo de certificado digital...");
        final String rutaRelativaCertificado = String.join("/", getRutaCertificadoDigital(rnc),nombreArchivo);
        String rutaAbsolute = saveArchivoLicenciaPort.save(rutaRelativaCertificado, archivo);

        // Actualizar los datos de la licencia con la información del certificado
        log.info("[5] Actualizando información de la licencia con los datos del certificado.");
        licencia.actualizarDatosCertificado(rutaAbsolute, nombreArchivo, password);

        log.info("[6] Guardando cambios en el repositorio de licencia.");
        licenciaRepositoryPort.save(licencia);
    }
}
