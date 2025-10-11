package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Response;

import lombok.Builder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.SetupStatusEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

@Builder
public record LicenciaResponseDto(
        Long id,
        String rncEmpresa,
        String razonSocial,
        String direccionFiscal,
        String alias,
        String nombreContacto,
        String telefonoContacto,
        AmbienteEnum ambiente,
        SetupStatusEnum setupBdStatus,
        SetupStatusEnum setupDirectoriesStatus,
        Boolean isActive
) {
}
