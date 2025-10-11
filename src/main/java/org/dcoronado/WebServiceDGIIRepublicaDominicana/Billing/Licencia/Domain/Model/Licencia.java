package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model;

import lombok.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.SetupStatusEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Licencia {

    // IDENTIFICACIÓN Y DATOS FISCALES
    private Long id;
    private String rnc;
    private String razonSocial;
    private String direccionFiscal;
    private AmbienteEnum ambiente;
    private Boolean isActive;

    // DATOS DE CONTACTO
    private String alias;
    private String nombreContacto;
    private String telefonoContacto;

    // DATOS DE CERTIFICADO DIGITAL
    private String nombreCertificado;
    private String claveCertificado;
    private String rutaCertificado;

    // CONFIGURACIÓN DE BASE DE DATOS
    private String hostBd;
    private String puertoBd;
    private String urlConexionBd;
    private String nombreBd;
    private String usuarioBd;
    private String passwordBd;

    // ESTADO DE SETUP
    private SetupStatusEnum databaseSetupStatus;
    private SetupStatusEnum directoriesSetupStatus;
    private LocalDateTime databaseSetupAt;
    private LocalDateTime directoriesSetupAt;


    /**
     * Genera automáticamente el nombres de la BD, usuario y password
     * basados en el RNC de la licencia.
     */
    public void generarDatosBD() {
        this.nombreBd = "fe_rd_V2_" + this.rnc;
        this.usuarioBd = "fe_rd_user_V2_" + this.rnc;
        this.passwordBd = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    /**
     * Configura el host y puerto de la base de datos.
     */
    public void configurarHostBD(String hostBd, String puertoBd) {
        notBlank(hostBd, "hostBd required");
        notBlank(puertoBd, "puertoBd required");
        this.hostBd = hostBd;
        this.puertoBd = puertoBd;
    }

    /**
     * Actualiza los datos editables de la licencia.
     * No incluye datos técnicos como RNC o configuración de BD.
     */
    public void actualizarDatos(String razonSocial, String direccionFiscal,
                                         String alias, String nombreContacto,
                                         String telefonoContacto, AmbienteEnum ambiente) {
        this.razonSocial = razonSocial;
        this.direccionFiscal = direccionFiscal;
        this.alias = alias;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.ambiente = ambiente;
    }

    /**
     * Actualiza la información del certificado digital.
     */
    public void actualizarDatosCertificado(String rutaCertificado, String nombreCertificado,
                                      String claveCertificado) {
        notBlank(rutaCertificado, "rutaCertificado required");
        notBlank(nombreCertificado, "nombreCertificado required");
        notBlank(claveCertificado, "claveCertificado required");

        this.rutaCertificado = rutaCertificado;
        this.nombreCertificado = nombreCertificado;
        this.claveCertificado = claveCertificado;
    }


    /**
     * Marca el setup de base de datos como completado.
     */
    public void marcarSetupBDCompletado() {
        this.databaseSetupStatus = SetupStatusEnum.COMPLETED;
        this.databaseSetupAt = LocalDateTime.now();
    }

    /**
     * Marca el setup de directorios como completado.
     */
    public void marcarSetupDirectoriosCompletado() {
        this.directoriesSetupStatus = SetupStatusEnum.COMPLETED;
        this.directoriesSetupAt = LocalDateTime.now();
    }

    /**
     * Verifica si algún setup ha sido completado.
     */
    public boolean tieneAlgunSetupCompletado() {
        return databaseSetupStatus == SetupStatusEnum.COMPLETED
                || directoriesSetupStatus == SetupStatusEnum.COMPLETED;
    }

    /**
     * Verifica si el setup de BD está completado.
     */
    public boolean tieneSetupBDCompletado() {
        return databaseSetupStatus == SetupStatusEnum.COMPLETED;
    }

    /**
     * Verifica si el setup de directorios está completado.
     */
    public boolean tieneSetupDirectoriosCompletado() {
        return directoriesSetupStatus == SetupStatusEnum.COMPLETED;
    }


    /**
     * Valida que la licencia puede ser actualizada.
     * Lanza excepción si ya tiene setup completado.
     */
    public void validarPuedeActualizar() {
        if (tieneAlgunSetupCompletado()) {
            throw new InvalidArgumentException(
                    "No se puede actualizar la licencia porque ya tiene setup completado. " +
                            "Database: %s, Directories: %s"
                                    .formatted(databaseSetupStatus, directoriesSetupStatus)
            );
        }
    }

    /**
     * Valida los datos básicos requeridos de la licencia.
     */
    public void validarDatosBasicos() {
        notBlank(this.rnc, "RNC required");
        notBlank(this.razonSocial, "Razon social required");
        notBlank(this.direccionFiscal, "Direccion Fiscal required");
        required(this.ambiente, "Ambiente required");
    }

    /**
     * Valida que la URL de conexión esté presente.
     */
    public void validarUrlConexionBD() {
        notBlank(this.urlConexionBd, "urlConexionBd required");
    }

    public void validarDatosParaFirma() {
        notBlank(this.rutaCertificado, "rutaCertificado required");
        notBlank(this.claveCertificado, "claveCertificado social required");
    }
}