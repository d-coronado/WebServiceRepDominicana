package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Transformer;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Response.LicenciaResponseDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Dto.Transformer.DtoTransformer;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LicenciaDtoTransformer extends DtoTransformer<LicenciaResponseDto, Licencia> {

    @Override
    public LicenciaResponseDto fromObject(Licencia licencia) {
        if (isNull(licencia)) throw new InvalidArgumentException("Licencia no puede ser null");

        return LicenciaResponseDto.builder()
                .id(licencia.getId())
                .rncEmpresa(licencia.getRnc())
                .razonSocial(licencia.getRazonSocial())
                .direccionFiscal(licencia.getDireccionFiscal())
                .alias(licencia.getAlias())
                .nombreContacto(licencia.getNombreContacto())
                .telefonoContacto(licencia.getTelefonoContacto())
                .ambiente(licencia.getAmbiente())
                .setupBdStatus(licencia.getDatabaseSetupStatus())
                .setupDirectoriesStatus(licencia.getDirectoriesSetupStatus())
                .isActive(licencia.getIsActive())
                .build();
    }
}
