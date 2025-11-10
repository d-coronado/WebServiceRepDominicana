package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model;

import lombok.Getter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.SetupStatusEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.CertificadoDigital;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.ConfiguracionBD;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.AlreadyExistsException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.DocumentFile;

import java.time.LocalDateTime;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.*;

@Getter
public class Licencia {

    // IDENTIFICACIÓN Y DATOS FISCALES
    private final Long id;
    private RNC rnc;
    private String razonSocial;
    private String direccionFiscal;
    private AmbienteEnum ambiente;
    private final Boolean isActive;

    // DATOS DE CONTACTO
    private String alias;
    private String nombreContacto;
    private String telefonoContacto;

    // DATOS DE CERTIFICADO DIGITAL (inmutable una vez configurado)
    private CertificadoDigital certificadoDigital;

    // CONFIGURACIÓN DE BASE DE DATOS (inmutable una vez configurado)
    private ConfiguracionBD configuracionBD;

    // ESTADO DE SETUP
    private SetupStatusEnum databaseSetupStatus;
    private SetupStatusEnum directoriesSetupStatus;
    private LocalDateTime databaseSetupAt;
    private LocalDateTime directoriesSetupAt;

    private Licencia(Long id, RNC rnc, String razonSocial, String direccionFiscal,
                     AmbienteEnum ambiente, Boolean activa, String alias,
                     String nombreContacto, String telefonoContacto,
                     CertificadoDigital certificadoDigital, ConfiguracionBD configuracionBD,
                     SetupStatusEnum databaseSetupStatus, SetupStatusEnum directoriesSetupStatus,
                     LocalDateTime databaseSetupAt, LocalDateTime directoriesSetupAt) {
        this.id = id;
        this.rnc = rnc;
        this.razonSocial = razonSocial;
        this.direccionFiscal = direccionFiscal;
        this.ambiente = ambiente;
        this.isActive = activa;
        this.alias = alias;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.certificadoDigital = certificadoDigital;
        this.configuracionBD = configuracionBD;
        this.databaseSetupStatus = databaseSetupStatus;
        this.directoriesSetupStatus = directoriesSetupStatus;
        this.databaseSetupAt = databaseSetupAt;
        this.directoriesSetupAt = directoriesSetupAt;
    }

    // FACTORY: Para CREAR nueva licencia (solo lo necesario para crear)
    public static Licencia crear(RNC rnc, String razonSocial,
                                 String direccionFiscal, AmbienteEnum ambiente,
                                 String alias, String nombreContacto, String telefonoContacto) {
        notBlank(razonSocial, "Razón social requerida");
        notBlank(direccionFiscal, "Dirección fiscal requerida");
        required(ambiente, "Ambiente requerido");
        return new Licencia(
                null,
                rnc,
                safeTrim(razonSocial),
                safeTrim(direccionFiscal),
                ambiente,
                true,
                safeTrim(alias),
                safeTrim(nombreContacto),
                safeTrim(telefonoContacto),
                null, // se configura después con uploadCertificado()
                null, // se configura después con setupBaseDatos()
                SetupStatusEnum.PENDING,
                SetupStatusEnum.PENDING,
                null,
                null
        );
    }

    // FACTORY: Para RECONSTRUIR desde BD (todos los campos)
    public static Licencia reconstruir(Long id, RNC rnc, String razonSocial,
                                       String direccionFiscal, AmbienteEnum ambiente,
                                       boolean activa, String alias, String nombreContacto,
                                       String telefonoContacto, CertificadoDigital certificadoDigital,
                                       ConfiguracionBD configuracionBD,
                                       SetupStatusEnum databaseSetupStatus,
                                       SetupStatusEnum directoriesSetupStatus,
                                       LocalDateTime databaseSetupAt,
                                       LocalDateTime directoriesSetupAt) {
        return new Licencia(
                id,
                rnc,
                razonSocial,
                direccionFiscal,
                ambiente,
                activa,
                alias,
                nombreContacto,
                telefonoContacto,
                certificadoDigital,
                configuracionBD,
                databaseSetupStatus,
                directoriesSetupStatus,
                databaseSetupAt,
                directoriesSetupAt);
    }

    // ============= MÉTODOS DE NEGOCIO =============

    // Queries sobre el estado del setup
    public boolean tieneAlgunSetupCompletado() {
        return databaseSetupStatus == SetupStatusEnum.COMPLETED
                || directoriesSetupStatus == SetupStatusEnum.COMPLETED;
    }

    public boolean tieneSetupDirectoriosCompletado() {
        return directoriesSetupStatus == SetupStatusEnum.COMPLETED;
    }

    /**
     * Valida que la licencia pueda ser actualizada.
     * Solo se permite cambiar el RNC si la licencia no tiene ningún setup completado.
     * Si ya tiene algún setup (base de datos o directorios), el RNC no puede modificarse.
     */
    private void validarPuedeActualizar(RNC nuevoRnc) {
        boolean intentaCambiarRnc = !this.rnc.equals(nuevoRnc);

        if (intentaCambiarRnc && tieneAlgunSetupCompletado()) {
            throw new InvalidArgumentException(
                    "No se puede cambiar el RNC de la licencia porque ya cuenta con algún setup completado. " +
                            "Database: %s, Directories: %s"
                                    .formatted(databaseSetupStatus, directoriesSetupStatus)
            );
        }
    }

    public void actualizarDatos(RNC rnc, String razonSocial, String direccionFiscal,
                                String alias, String nombreContacto,
                                String telefono, AmbienteEnum ambiente) {
        notBlank(razonSocial, "Razón social requerida");
        notBlank(direccionFiscal, "Dirección fiscal requerida");
        required(ambiente, "Ambiente requerido");
        validarPuedeActualizar(rnc);
        this.rnc = rnc;
        this.razonSocial = safeTrim(razonSocial);
        this.direccionFiscal = safeTrim(direccionFiscal);
        this.ambiente = ambiente;
        this.alias = safeTrim(alias);
        this.nombreContacto = safeTrim(nombreContacto);
        this.telefonoContacto = safeTrim(telefono);
    }

    /**
     * Genera y configura la base de datos de la licencia.
     *
     * @param host   Host del servidor de base de datos
     * @param puerto Puerto del servidor de base de datos
     * @throws AlreadyExistsException si el setup ya fue completado o los datos son inválidos
     */
    public void prepararSetupBD(String host, String puerto) {

        if (this.configuracionBD != null && this.configuracionBD.existe())
            throw new AlreadyExistsException("La configuración de base de datos ya existe");

        if (this.databaseSetupStatus == SetupStatusEnum.COMPLETED)
            throw new AlreadyExistsException("El setup de base de datos ya fue completado");

        // Generar configuración usando el Value Object
        this.configuracionBD = ConfiguracionBD.generar(this.rnc, host, puerto);
        this.databaseSetupStatus = SetupStatusEnum.COMPLETED;
        this.databaseSetupAt = LocalDateTime.now();
    }

    public void prepararSetupDirectorios() {
        if (this.directoriesSetupStatus == SetupStatusEnum.COMPLETED)
            throw new AlreadyExistsException("El setup de directorios ya fue completado");
        this.directoriesSetupStatus = SetupStatusEnum.COMPLETED;
        this.directoriesSetupAt = LocalDateTime.now();
    }

    /**
     * Prepara los datos del certificado digital antes de guardarlo físicamente
     */
    public void prepararCertificadoDigital(DocumentFile documentFile, String claveCertificado) {

        if (this.directoriesSetupStatus == SetupStatusEnum.PENDING)
            throw new AlreadyExistsException(
                    "No se puede subir la licencia porque la creación de directorios aún está pendiente. " +
                            "Estado actual: %s".formatted(this.directoriesSetupStatus.name())
            );

        this.certificadoDigital = CertificadoDigital.crear(documentFile, claveCertificado);
    }

    /**
     * Confirma el certificado digital con la ruta absoluta después de guardarlo físicamente.
     */
    public void confirmarCertificadoDigital(String rutaCertificado) {
        this.certificadoDigital = this.certificadoDigital.conRuta(rutaCertificado);
    }

    public void puedeRealizarFirmaDocumento() {
        if (this.certificadoDigital == null || !this.certificadoDigital.estaListoParaFirmar()) {
            throw new InvalidArgumentException("No se puede firmar el documento: certificado no listo.");
        }
    }

}