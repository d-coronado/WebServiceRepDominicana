package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Persistence;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LicenciaMapper {

    public LicenciaEntity toEntity(Licencia model) {
        if (model == null) return null;

        return LicenciaEntity.builder()
                .id(model.getId()) // puede ser null en creación
                .rnc(model.getRnc())
                .razonSocial(model.getRazonSocial())
                .direccionFiscal(model.getDireccionFiscal())
                .alias(model.getAlias())
                .nombreContacto(model.getNombreContacto())
                .telefonoContacto(model.getTelefonoContacto())
                .rutaCertificado(model.getRutaCertificado())
                .nombreCertificado(model.getNombreCertificado())
                .claveCertificado(model.getClaveCertificado())
                .hostBd(model.getHostBd())
                .puertoBd(model.getPuertoBd())
                .urlConexionBd(model.getUrlConexionBd())
                .nombreBd(model.getNombreBd())
                .usuarioBd(model.getUsuarioBd())
                .passwordBd(model.getPasswordBd())
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

        return Licencia.builder()
                .id(entity.getId())
                .rnc(entity.getRnc())
                .razonSocial(entity.getRazonSocial())
                .direccionFiscal(entity.getDireccionFiscal())
                .alias(entity.getAlias())
                .nombreContacto(entity.getNombreContacto())
                .telefonoContacto(entity.getTelefonoContacto())
                .rutaCertificado(entity.getRutaCertificado())
                .nombreCertificado(entity.getNombreCertificado())
                .claveCertificado(entity.getClaveCertificado())
                .hostBd(entity.getHostBd())
                .puertoBd(entity.getPuertoBd())
                .urlConexionBd(entity.getUrlConexionBd())
                .nombreBd(entity.getNombreBd())
                .usuarioBd(entity.getUsuarioBd())
                .passwordBd(entity.getPasswordBd())
                .ambiente(entity.getAmbiente())
                .databaseSetupAt(entity.getDatabaseSetupAt())
                .databaseSetupStatus(entity.getDatabaseSetupStatus())
                .directoriesSetupAt(entity.getDirectoriesSetupAt())
                .directoriesSetupStatus(entity.getDirectoriesSetupStatus())
                .isActive(entity.getIsActive())
                .build();
    }
}
