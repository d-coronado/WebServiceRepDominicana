package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record FormaPagoRequestDto(

        Integer formapago,

        @PositiveOrZero(message = "{field.positive}")
        BigDecimal monto
) {}
