package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest;

import lombok.SneakyThrows;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request.LicenciaRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request.LicenciaSetupBDRequestDto;
import org.springframework.stereotype.Component;

@Component
public class LicenciaMapperCommand {

    /**
     * Convierte un request de creación a command.
     */
    public CreateLicenciaCommand toCommand(LicenciaRequestDto request) {
        return new CreateLicenciaCommand(
                request.rnc(),
                request.razonSocial(),
                request.direccionFiscal(),
                request.alias(),
                request.nombreContacto(),
                request.telefonoContacto(),
                request.ambiente()
        );
    }

    /**
     * Convierte un request de actualización a command.
     */
    public UpdateLicenciaCommand toCommand(Long id, LicenciaRequestDto request) {
        return new UpdateLicenciaCommand(
                id,
                request.rnc(),
                request.razonSocial(),
                request.direccionFiscal(),
                request.alias(),
                request.nombreContacto(),
                request.telefonoContacto(),
                request.ambiente()
        );
    }

    /**
     * Convierte un request de configuración de BD a command.
     */
    public SetupBDCommand toCommand(LicenciaSetupBDRequestDto request) {
        return new SetupBDCommand(
                request.rnc(),
                request.host(),
                request.port()
        );
    }

    /**
     * Convierte un archivo y contraseña a command de certificado.
     */
    @SneakyThrows
    public UploadCertificadoDigitalCommand toCommand(String rnc, String nombreArchivo, byte[] archivo, String contrasenia) {
        return new UploadCertificadoDigitalCommand(
                rnc,
                nombreArchivo,
                archivo,
                contrasenia
        );
    }


    @SneakyThrows
    public FirmarDocumentoCommand toCommand(String rnc, String nombreArchivo, byte[] archivo) {
        return new FirmarDocumentoCommand(
                rnc,
                nombreArchivo,
                archivo
        );
    }

}
