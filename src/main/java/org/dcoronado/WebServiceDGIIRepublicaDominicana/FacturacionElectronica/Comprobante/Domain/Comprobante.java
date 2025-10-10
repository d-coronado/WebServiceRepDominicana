package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado.Encabezado;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.util.List;

import static java.util.Objects.isNull;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

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

        required(ambienteEnum, "ambienteEnum required");
        if (isNull(AmbienteEnum.fromCodigoOrNull(ambienteEnum.getCodigo())))
            throw new InvalidArgumentException("Ambiente inválido: " + ambienteEnum);

        required(tipoComprobanteTributarioEnum, "Tipo de comprobante required");
        if (isNull(TipoComprobanteTributarioEnum.fromValorOrNull(tipoComprobanteTributarioEnum.getValor()))) {
            throw new InvalidArgumentException("Tipo de comprobante inválido: " + tipoComprobanteTributarioEnum);
        }

        required(encabezado, "encabezado required");
        required(items, "items required");
    }
}
