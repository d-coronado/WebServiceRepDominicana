package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UploadCertificadoUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.UploadCertificatePort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.PathsDirectory.getRelativaCertificadoLicencia;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.validateArchivo;


/**
 * Servicio de aplicación encargado de cargar y registrar
 * un certificado digital asociado a una licencia.
 * Se encarga de validar los datos de entrada, verificar la existencia de la licencia,
 * guardar el archivo del certificado en la ruta correspondiente
 * y actualizar la información de la licencia con los datos del certificado.
 */
@Service
@RequiredArgsConstructor
public class UploadCertificadoLicenciaService implements UploadCertificadoUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final UploadCertificatePort uploadCertificatePort;


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
    public void execute(String rnc, String nombreArchivo, byte[] archivo, String password) throws IOException {
        // Validar parámetros de entrada
        notBlank(rnc, "RNC required");
        notBlank(password, "Password required");
        validateArchivo(nombreArchivo, archivo);

        // Buscar licencia y validar existencia
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new NotFoundException("Licencia con RNC " + rnc + " no encontrada"));

        // Buscar licencia y validar existencia
        String rutaRelativaCertificado = getRelativaCertificadoLicencia(rnc, nombreArchivo);
        String rutaAbsolute = uploadCertificatePort.save(rutaRelativaCertificado, archivo);

        // Actualizar los datos de la licencia con la información del certificado
        licencia.actualizarDatosCertificado(rutaAbsolute, nombreArchivo, password);
        licenciaRepositoryPort.save(licencia);
    }
}
