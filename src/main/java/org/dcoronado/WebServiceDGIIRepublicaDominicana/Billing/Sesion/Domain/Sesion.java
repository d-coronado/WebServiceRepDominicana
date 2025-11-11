package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

import java.time.OffsetDateTime;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Util.FechaUtil.parseUtcStringToOffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sesion {

    private Long id;
    private String rnc;
    private AmbienteEnum ambiente;
    private OffsetDateTime expedido; // SIEMPRE EN UTC
    private OffsetDateTime expira; // SIEMPRE EN UTC
    private String token;

    public void validarParametrosGenericos() {
        notBlank(this.rnc, "RNC required");
        required(this.ambiente, "Ambiente required");
    }

    public void validarAccesLimitAmbienteLicencia(AmbienteEnum ambienteLicencia) {
        required(ambienteLicencia, "Licencia encontrada no cuenta con un ambienteEnum valido");
        if (this.ambiente == AmbienteEnum.PRODUCCION && !ambienteLicencia.equals(AmbienteEnum.PRODUCCION))
            throw new InvalidArgumentException("Licencia encontrada no cuenta acceso a entornos productivos");
    }

    public void validarLicenciaRequireForSesion(String pathCertificadoDigital, String claveCertificado) {
        notBlank(pathCertificadoDigital, "Licencia encontrada no cuenta con una ruta de certificado valida");
        notBlank(claveCertificado, "Licencia encontrada no cuenta con una clave de certificado valido");
    }

    public void validarDatosObtenidosSesion(String token, String fechaExpedido, String fechaExpira) {
        notBlank(token, "Token obtenedio por Dgii no es valido");
        notBlank(fechaExpedido, "Fecha de expedido obtenido por DGII no es valido");
        notBlank(fechaExpira, "Fecha de expiracion obtenido por DGII no es valido");
    }

    public void setDatosSesion(String token, String fechaExpedido, String fechaExpira) {
        validarDatosObtenidosSesion(token, fechaExpedido, fechaExpira);
        this.token = token;
        this.expedido = parseUtcStringToOffsetDateTime(fechaExpedido);
        this.expira = parseUtcStringToOffsetDateTime(fechaExpira);
    }


}
