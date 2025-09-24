package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Persistence;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LicenciaMapper {
    public LicenciaEntity toEntity(Licencia model) {
        if (model == null) return null;

        LicenciaEntity entity = new LicenciaEntity();
        entity.setId(model.getId()); // En creación puede ser null
        entity.setRnc(model.getRnc());
        entity.setRazonSocial(model.getRazonSocial());
        entity.setDireccionFiscal(model.getDireccionFiscal());
        entity.setAlias(model.getAlias());
        entity.setNombreContacto(model.getNombreContacto());
        entity.setTelefonoContacto(model.getTelefonoContacto());
        entity.setRutaCertificado(model.getRutaCertificado());
        entity.setNombreCertificado(model.getNombreCertificado());
        entity.setClaveCertificado(model.getClaveCertificado());
        entity.setHostBd(model.getHostBd());
        entity.setPuertoBd(model.getPuertoBd());
        entity.setUrlConexionBd(model.getUrlConexionBd());
        entity.setNombreBd(model.getNombreBd());
        entity.setUsuarioBd(model.getUsuarioBd());
        entity.setPasswordBd(model.getPasswordBd());
        entity.setAmbiente(model.getAmbiente().getCodigo());
        entity.setIsActive(model.getIsActive());
        return entity;
    }

    public Licencia toDomain(LicenciaEntity entity) {
        if (isNull(entity)) return null;

        Licencia model = new Licencia();
        model.setId(entity.getId());
        model.setRnc(entity.getRnc());
        model.setRazonSocial(entity.getRazonSocial());
        model.setDireccionFiscal(entity.getDireccionFiscal());
        model.setAlias(entity.getAlias());
        model.setNombreContacto(entity.getNombreContacto());
        model.setTelefonoContacto(entity.getTelefonoContacto());
        model.setRutaCertificado(entity.getRutaCertificado());
        model.setNombreCertificado(entity.getNombreCertificado());
        model.setClaveCertificado(entity.getClaveCertificado());
        model.setHostBd(entity.getHostBd());
        model.setPuertoBd(entity.getPuertoBd());
        model.setUrlConexionBd(entity.getUrlConexionBd());
        model.setNombreBd(entity.getNombreBd());
        model.setUsuarioBd(entity.getUsuarioBd());
        model.setPasswordBd(entity.getPasswordBd());
        model.setAmbiente(Ambiente.fromCodigoOrNull(entity.getAmbiente()));
        model.setIsActive(entity.getIsActive());
        return model;
    }

}
