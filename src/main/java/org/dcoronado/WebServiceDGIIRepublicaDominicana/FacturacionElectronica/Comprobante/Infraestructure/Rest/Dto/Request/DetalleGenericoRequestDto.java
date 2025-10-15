package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public record DetalleGenericoRequestDto(

        String nroLineaItem,

        List<CodificacionGenericRequestDto> codificacionesItem,

        @NotNull(message = "{}")
        Integer indicadorFacturacion,

        RetencionRequestDto retencion,

        @NotNull(message = "{}")
        @Size(max = 80, message = "{}")
        String descripcionItem,

        @NotNull(message = "{}")
        Integer indicadorBienServicio,

        @Size(max = 2000, message = "{}")
        String descrpcionAdicionalItem,

        BigDecimal cantidadItem,
        Integer unidadMedida,
        Integer cantidadEmpaqueReferenciaISC,
        Integer unidadEmpaqueReferenciaISC,

        List<SubCantidadRequestDto> distribucionSubCantidades,

        @Positive(message = "{}")
        BigDecimal gradosAlcohol,

        BigDecimal precioUnitarioReferencia,

        String fechaElaboracion,
        String fechaVencimiento,

        @PositiveOrZero(message = "{}")
        BigDecimal precioUnitarioItem,

        @PositiveOrZero(message = "{}")
        BigDecimal montoTotalDescuento,

        // SUB.DESCUENTOS
        @Size(max = 12, message = "{}")
        List<SubDescuentoRequestDto> subDescuentos,

        BigDecimal montoTotalRecargo,

        // SUB.RECARGOS
        @Size(max = 12, message = "{}")
        List<SubRecargoRequestDto> subRecargos,

        @Size(max = 2, message = "{}")
        List<String> tiposImpuestosAdicionales,  // arreglo de Tipos de Impuesto

        OtraMonedaDetalleItemRequestDto otraMonedaDetale,

        BigDecimal montoItemLinea
) {}
