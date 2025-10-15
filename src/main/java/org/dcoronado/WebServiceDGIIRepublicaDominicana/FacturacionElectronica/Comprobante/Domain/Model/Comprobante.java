package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.Encabezado;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

@Builder
public record Comprobante(
        AmbienteEnum ambienteEnum,
        TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum,
        Encabezado encabezado,
        List<Item> items,
        List<SubTotal> subtotales,
        List<DescuentoORecargo> descuentosORecargos,
        List<PaginaSubTotal> paginas,
        InformacionReferencia informacionReferencia,
        String fechaHoraFirma
) {
    public Comprobante {
        /* El comprobante esta obligado a tener si o si estos campos */
        required(ambienteEnum, "ambienteEnum required");
        required(tipoComprobanteTributarioEnum, "Tipo de comprobante required");
        required(encabezado, "encabezado required");
        required(items, "items required");
    }
}
