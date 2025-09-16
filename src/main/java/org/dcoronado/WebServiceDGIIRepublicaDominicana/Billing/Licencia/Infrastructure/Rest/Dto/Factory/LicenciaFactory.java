package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Factory;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request.LicenciaRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LicenciaFactory {

    public Licencia ofDto(LicenciaRequestDto request) {
        if(isNull(request)) throw new InvalidArgumentException("DTO no puede ser null");
        Licencia licencia = new Licencia();
        this.updateOfDto(request,licencia);
        return licencia;
    }

    public Licencia fromDtoForUpdate(Long id, LicenciaRequestDto request) {
        if(isNull(id)) throw new InvalidArgumentException("ID no puede ser null para actualización");
        if(isNull(request)) throw new InvalidArgumentException("DTO no puede ser null");

        Licencia licencia = new Licencia();
        licencia.setId(id);
        this.updateOfDto(request, licencia);
        return licencia;
    }

    public void updateOfDto(LicenciaRequestDto request, Licencia licencia) {
        licencia.setRnc(request.rnc());
        licencia.setRazonSocial(request.razonSocial());
        licencia.setDireccionFiscal(request.direccionFiscal());
        licencia.setAlias(request.alias());
        licencia.setNombreContacto(request.nombreContacto());
        licencia.setTelefonoContacto(request.telefonoContacto());
        licencia.setAmbiente(request.ambiente().getCodigo());
    }

}
