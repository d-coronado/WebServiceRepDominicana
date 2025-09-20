package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

import java.util.UUID;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Licencia {
    private Long id;
    private String rnc;
    private String razonSocial;
    private String direccionFiscal;
    private String alias;
    private String nombreContacto;
    private String telefonoContacto;
    private String nombreCertificado;
    private String claveCertificado;
    private String rutaCertificado;
    private String hostBd;
    private String puertoBd;
    private String urlConexionBd;
    private String nombreBd;
    private String usuarioBd;
    private String passwordBd;
    private String ambiente;
    private Boolean isActive;

    public void setUrlConexionBD(String hostBd, String puertoBd) {
        this.hostBd = hostBd;
        this.puertoBd = puertoBd;
    }

    public void setDatosBD() {
        this.nombreBd = "fe_rd_V2_" + this.rnc;
        this.usuarioBd = "fe_rd_user_V2_" + this.rnc;
        this.passwordBd = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    public void validarGeneric() {
        notBlank(this.rnc, "RNC required");
        notBlank(this.razonSocial, "Razon social required");
        notBlank(this.direccionFiscal, "Direccion Fiscal required");
    }

    public void validarDatosConexionBD() {
        notBlank(this.hostBd, "hostBd required");
        notBlank(this.puertoBd, "puertoBd required");
        notBlank(this.nombreBd, "nombreBd required");
        notBlank(this.usuarioBd, "usuarioBd required");
        notBlank(this.passwordBd, "passwordBd required");
    }

    /* Manejo aca el actualizar datos debido a que es parte de logica de negocio solo actualizar campos que no tienen
    * que ver con el conexiones a BD*/
    public void actualizarDatos(Licencia licencia) {
        this.razonSocial = licencia.getRazonSocial();
        this.direccionFiscal = licencia.getDireccionFiscal();
        this.alias = licencia.getAlias();
        this.nombreContacto = licencia.getNombreContacto();
        this.telefonoContacto = licencia.getTelefonoContacto();
        this.ambiente = licencia.getAmbiente();
    }

    public void actualizarDatosCertificado(String rutaCertificado, String nombreCertificado,String claveCertificado) {
        notBlank(rutaCertificado, "rutaCertificado required");
        notBlank(nombreCertificado, "nombreCertificado required");
        notBlank(claveCertificado, "claveCertificado required");
        this.rutaCertificado = rutaCertificado;
        this.nombreCertificado = nombreCertificado;
        this.claveCertificado = claveCertificado;
    }

    public void validarAccesoProduccion(Ambiente ambienteSolicitado) {
        if (Ambiente.PRODUCCION.equals(ambienteSolicitado) && !Ambiente.PRODUCCION.getCodigo().equalsIgnoreCase(this.ambiente)) {
            throw new InvalidArgumentException("LICENCIA NO TIENE ACCESO A ENTORNOS PRODUCTIVOS");
        }
    }

    public void validarParametrosCertificadoDigital(){
        notBlank(this.rutaCertificado, "rutaCertificado required");
        notBlank(this.claveCertificado, "claveCertificado required");
    }

}
