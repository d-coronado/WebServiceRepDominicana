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
        if(isNull(licencia)) throw new InvalidArgumentException("Licencia no puede ser null");
        return new LicenciaResponseDto(
            licencia.getId(),
            licencia.getRnc(),
            licencia.getRazonSocial(),
            licencia.getDireccionFiscal(),
            licencia.getAlias(),
            licencia.getNombreContacto(),
            licencia.getTelefonoContacto(),
            licencia.getAmbiente().getCodigo(),
            licencia.getIsActive()
        );
    }
}
