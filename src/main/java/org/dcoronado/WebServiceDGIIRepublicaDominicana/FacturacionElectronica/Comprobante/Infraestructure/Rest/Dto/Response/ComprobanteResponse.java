package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Response;

import lombok.Builder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

import java.math.BigDecimal;

@Builder
public record ComprobanteResponse (
        AmbienteEnum ambienteEnum,
        String rncEmisor,
        String rncComprador,
        String encF,
        String fechaEmision,
        BigDecimal monto,
        String fechaHoraFirma,
        String codigoSeguridad,
        String urlConsultaDgii
)
{
}
