package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Factory;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request.LicenciaRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request.LicenciaSetupBDRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LicenciaFactory {

    public Licencia ofDto(LicenciaRequestDto request) {
        if (isNull(request)) throw new InvalidArgumentException("DTO no puede ser null");

        return Licencia.builder()
                .rnc(request.rnc())
                .razonSocial(request.razonSocial())
                .direccionFiscal(request.direccionFiscal())
                .alias(request.alias())
                .nombreContacto(request.nombreContacto())
                .telefonoContacto(request.telefonoContacto())
                .ambiente(request.ambiente())
                .build();
    }

    public Licencia ofDto(LicenciaSetupBDRequestDto request) {
        if (isNull(request)) throw new InvalidArgumentException("DTO no puede ser null");

        return Licencia.builder()
                .rnc(request.rnc())
                .hostBd(request.host())
                .puertoBd(request.port())
                .build();
    }

    public Licencia fromDtoForUpdate(Long id, LicenciaRequestDto request) {
        if (isNull(id)) throw new InvalidArgumentException("ID no puede ser null para actualización");
        if (isNull(request)) throw new InvalidArgumentException("DTO no puede ser null");

        return Licencia.builder()
                .id(id)
                .rnc(request.rnc())
                .razonSocial(request.razonSocial())
                .direccionFiscal(request.direccionFiscal())
                .alias(request.alias())
                .nombreContacto(request.nombreContacto())
                .telefonoContacto(request.telefonoContacto())
                .ambiente(request.ambiente())
                .build();
    }
}
