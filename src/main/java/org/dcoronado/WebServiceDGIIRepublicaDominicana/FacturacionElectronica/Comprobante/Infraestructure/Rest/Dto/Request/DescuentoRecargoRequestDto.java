package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record DescuentoRecargoRequestDto(

        Integer numeroLinea, // Representa "NumeroLinea"

        @Size(max = 1, message = "{}")
        String tipoAjuste, // Representa "TipoAjuste"

        @Size(max = 45, message = "{}")
        String descripcion,

        Integer indicadorNorma1007, // Representa "IndicadorNorma1007"

        @Size(max = 1, message = "{}")
        String tipoValor, // Representa "TipoValor"

        BigDecimal valorDescuentoORecargo, // Representa "ValorDescuentooRecargo"
        BigDecimal montoDescuentoORecargo, // Representa "MontoDescuentooRecargo"
        BigDecimal montoDescuentooRecargoOtraMoneda, // Representa "MontoDescuentooRecargoOtraMoneda"
        Integer indicadorFacturacionDescuentoORecargo // Representa "IndicadorFacturacionDescuentooRecargo"
) {}
