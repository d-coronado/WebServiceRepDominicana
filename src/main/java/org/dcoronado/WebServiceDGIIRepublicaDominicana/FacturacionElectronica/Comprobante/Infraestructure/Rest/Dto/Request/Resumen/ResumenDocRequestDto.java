package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Resumen;

import jakarta.validation.constraints.Size;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic.FormaPagoRequestDto;

import java.util.List;

public record ResumenDocRequestDto(
        String entornoProduccion,
        Integer tipoComprobante,
        String secuencia,
        String tipoIngreso,
        Integer tipoPago,
        @Size(max = 7, message = "{}")
        List<FormaPagoRequestDto> formasPago,
        String fechaEmision,
        String razonsocialEmisor
) {}
