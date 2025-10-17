package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.util.List;

public record DocRequestDto(

        @JsonProperty("entornoProduccion")
        AmbienteEnum entornoProduccion,

        @JsonProperty("tipoComprobante")
        TipoComprobanteTributarioEnum tipoComprobante,

        @JsonProperty("secuencia")
        String secuencia,

        @JsonProperty("fechaVencimiento")
        String fechaVencimiento,

        @JsonProperty("indicadorNotaCredito")
        Integer indicadorNotaCredito,

        @JsonProperty("indicadorEnvioDiferido")
        Integer indicadorEnvioDiferido,

        @JsonProperty("indicadorMontoGravado")
        Integer indicadorMontoGravado,

        @JsonProperty("tipoIngreso")
        String tipoIngreso,

        @JsonProperty("tipoPago")
        Integer tipoPago,

        @JsonProperty("fechaLimitePago")
        String fechaLimitePago,

        @JsonProperty("terminoPago")
        @Size(max = 15)
        String terminoPago,

        @JsonProperty("formasPago")
        @Size(max = 7, message = "{}")
        List<FormaPagoRequestDto> formasPago,

        @JsonProperty("tipoCuentaPago")
        String tipoCuentaPago,

        @JsonProperty("numeroCuentaPago")
        String numeroCuentaPago,

        @JsonProperty("bancoPago")
        String bancoPago,

        @JsonProperty("fechaPeriodoServicioDesde")
        String fechaPeriodoServicioDesde,

        @JsonProperty("fechaPeriodoServicioHasta")
        String fechaPeriodoServicioHasta,

        @NotNull
        @Positive(message = "{}")
        @JsonProperty("totalPaginas")
        Integer totalPaginas
) {}
