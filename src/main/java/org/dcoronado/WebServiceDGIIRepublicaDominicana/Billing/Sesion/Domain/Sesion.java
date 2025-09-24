package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

import java.time.OffsetDateTime;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notNull;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils.FechaUtil.parseUtcStringToOffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sesion {

    private Long id;
    private String rnc;
    private Ambiente ambiente;
    private OffsetDateTime expedido; // SIEMPRE EN UTC
    private OffsetDateTime expira; // SIEMPRE EN UTC
    private String token;

    public void validarParametrosGenericos() {
        notBlank(this.rnc, "RNC required");
        notBlank(this.ambiente.getCodigo(), "Ambiente required");
    }

    public void validarAccesLimitAmbienteLicencia(Ambiente ambienteLicencia) {
        notNull(ambienteLicencia, "Ambiente required");
        if(this.ambiente == Ambiente.PRODUCCION && !ambienteLicencia.equals(Ambiente.PRODUCCION))
            throw new InvalidArgumentException("Licencia no cuenta acceso a entornos productivos");
    }

    public void validarLicenciaRequireForSesion(String pathCertificadoDigital, String claveCertificado) {
        notBlank(pathCertificadoDigital, "Path certificado digital required");
        notBlank(claveCertificado, "Clave certificado required");
    }

    public void validarDatosObtenidosSesion(String token,String fechaExpedido,String fechaExpira) {
        notBlank(token, "Token required");
        notBlank(fechaExpedido, "Fecha expedido required");
        notBlank(fechaExpira, "Fecha expira required");
    }

    public void setDatosSesion(String token,String fechaExpedido,String fechaExpira){
        validarDatosObtenidosSesion(token,fechaExpedido,fechaExpira);
        this.token = token;
        this.expedido = parseUtcStringToOffsetDateTime(fechaExpedido);
        this.expira = parseUtcStringToOffsetDateTime(fechaExpira);
    }


}
