package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Persistence;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.CertificadoDigital;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.ConfiguracionBD;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.DocumentFile;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LicenciaMapper {

    public LicenciaEntity toEntity(Licencia model) {
        if (model == null) return null;

        var certificado = model.getCertificadoDigital();
        var configuracionBD = model.getConfiguracionBD();

        return LicenciaEntity.builder()
                .id(model.getId())
                .rnc(model.getRnc().getValor())
                .razonSocial(model.getRazonSocial())
                .direccionFiscal(model.getDireccionFiscal())
                .alias(model.getAlias())
                .nombreContacto(model.getNombreContacto())
                .telefonoContacto(model.getTelefonoContacto())
                // Certificado digital
                .rutaCertificado(certificado != null ? certificado.getRutaCertificado() : null)
                .nombreCertificado(certificado != null ? certificado.getCertificado().getNombre() : null)
                .claveCertificado(certificado != null ? certificado.getClave() : null)
                // Configuración BD
                .hostBd(configuracionBD != null ? configuracionBD.getHostBD() : null)
                .puertoBd(configuracionBD != null ? configuracionBD.getPuertoBD() : null)
                .urlConexionBd(configuracionBD != null ? configuracionBD.getUrlConexion() : null)
                .nombreBd(configuracionBD != null ? configuracionBD.getNombreBD() : null)
                .usuarioBd(configuracionBD != null ? configuracionBD.getUsuario() : null)
                .passwordBd(configuracionBD != null ? configuracionBD.getPassword() : null)
                .ambiente(model.getAmbiente())
                .databaseSetupStatus(model.getDatabaseSetupStatus())
                .databaseSetupAt(model.getDatabaseSetupAt())
                .directoriesSetupStatus(model.getDirectoriesSetupStatus())
                .directoriesSetupAt(model.getDirectoriesSetupAt())
                .isActive(model.getIsActive())
                .build();
    }

    public Licencia toDomain(LicenciaEntity entity) {
        if (isNull(entity)) return null;

        // Reconstruir certificado digital
        CertificadoDigital certificado = CertificadoDigital.reconstruirDesdeBD(
                DocumentFile.reconstruir(entity.getNombreCertificado()),
                entity.getClaveCertificado(),
                entity.getRutaCertificado()
        );

        // Reconstruir configuración BD
        ConfiguracionBD configuracionBD = ConfiguracionBD.reconstruirDesdeBD(
                entity.getNombreBd(),
                entity.getHostBd(),
                entity.getPuertoBd(),
                entity.getUsuarioBd(),
                entity.getPasswordBd(),
                entity.getUrlConexionBd()
        );

        // Reconstruir dominio
        return Licencia.reconstruir(
                entity.getId(),
                RNC.reconstruirDesdeBD(entity.getRnc()),
                entity.getRazonSocial(),
                entity.getDireccionFiscal(),
                entity.getAmbiente(),
                entity.getIsActive(),
                entity.getAlias(),
                entity.getNombreContacto(),
                entity.getTelefonoContacto(),
                certificado,
                configuracionBD,
                entity.getDatabaseSetupStatus(),
                entity.getDirectoriesSetupStatus(),
                entity.getDatabaseSetupAt(),
                entity.getDirectoriesSetupAt()
        );
    }
}
