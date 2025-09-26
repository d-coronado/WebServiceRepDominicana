package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UploadCertificadoUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.UploadCertificatePort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Validator.ArchivoValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.PathsDirectory.getRelativaCertificadoLicencia;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Service
@RequiredArgsConstructor
public class UploadCertificadoLicenciaService implements UploadCertificadoUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final UploadCertificatePort uploadCertificatePort;

    @Override
    public void execute(String rnc,String nombreArchivo, byte[] archivo, String password) throws IOException {
        notBlank(rnc,"RNC required");
        notBlank(password,"Password required");
        ArchivoValidator.validate(nombreArchivo,archivo);
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new NotFoundException("Licencia con RNC " + rnc + " no encontrada"));
        String rutaRelativaCertificado = getRelativaCertificadoLicencia(rnc,nombreArchivo);
        String rutaAbsolute = uploadCertificatePort.save(rutaRelativaCertificado,archivo);
        licencia.actualizarDatosCertificado(rutaAbsolute,nombreArchivo,password);
        licenciaRepositoryPort.save(licencia);
    }
}
