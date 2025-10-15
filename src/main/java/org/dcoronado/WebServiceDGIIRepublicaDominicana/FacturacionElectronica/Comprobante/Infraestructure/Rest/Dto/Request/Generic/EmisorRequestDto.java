package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record EmisorRequestDto(

        @JsonProperty("rnc")
        String rnc,

        @JsonProperty("razonSocial")
        String razonSocial,

        @JsonProperty("nombreComercial")
        String nombreComercial,

        @JsonProperty("sucursal")
        String sucursal,

        @JsonProperty("direccion")
        String direccion,

        @JsonProperty("municipio")
        String municipio,

        @JsonProperty("provincia")
        String provincia,

        @JsonProperty("telefonos")
        @Size(max = 10, message = "{}") // puedes ajustar el límite según tu lógica
        List<String> telefonos,

        @JsonProperty("correo")
        String correo,

        @JsonProperty("website")
        String website,

        @JsonProperty("actividadEconomica")
        String actividadEconomica,

        @JsonProperty("codigoVendedor")
        String codigoVendedor,

        @JsonProperty("numeroFacturaInterna")
        String numeroFacturaInterna,

        @JsonProperty("numeroPedidoInterno")
        String numeroPedidoInterno,

        @JsonProperty("zonaVenta")
        String zonaVenta,

        @JsonProperty("rutaVenta")
        String rutaVenta,

        @JsonProperty("informacionAdicional")
        String informacionAdicional,

        @JsonProperty("fechaEmision")
        String fechaEmision
) {}
