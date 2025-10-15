package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ComprobanteGenericRequestDto(

        @JsonProperty("entornoProduccion")
        AmbienteEnum entornoProduccion,

        @JsonProperty("tipoComprobante")
        TipoComprobanteTributarioEnum tipoComprobante,

        @JsonProperty("encabezado")
        EncabezadoGenericoRequestDto encabezado,

        @JsonProperty("detalles")
        @Size(min = 1, max = 100, message = "{}")
        List<DetalleGenericoRequestDto> detalles,

        @JsonProperty("subTotales")
        @Size(max = 20, message = "{}")
        List<SubTotalGenericoRequestDto> subTotales,

        @JsonProperty("descuentosORecargos")
        List<DescuentoRecargoRequestDto> descuentosORecargos,

        @JsonProperty("paginas")
        List<PaginaSubTotalRequestDto> paginas,

        @JsonProperty("informacionReferencia")
        InformacionReferenciaRequestDto informacionReferencia
) {}
