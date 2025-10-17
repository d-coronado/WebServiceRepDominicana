package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils.FechaUtil;

import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

public final class ComprobanteBaseValidator {

    private ComprobanteBaseValidator() {
    }

    public static void execute(Comprobante comprobante) {
        required(comprobante, "Comprobante requerido");
        validarDatosGenerales(comprobante);
        validarEncabezadoGenerico(comprobante.getEncabezado(), comprobante.getFechaEmision());
        validarItemsGenerico(comprobante.getItems());
        validarSubtotalesGenerico(comprobante.getSubtotales());
        validarDescuentosORecargosGenerico(comprobante.getDescuentosORecargos());
        validarInformacionReferenciaGenerico(comprobante.getInformacionReferencia());
        validarPaginasGenerico(comprobante.getPaginas());
    }

    private static void validarDatosGenerales(Comprobante comprobante) {
        required(comprobante.getAmbienteEnum(), "El ambiente es obligatorio");
        required(comprobante.getTipoComprobanteTributarioEnum(), "El tipo de comprobante es obligatorio");
        required(comprobante.getFechaEmision(), "El fecha de emision es obligatorio");
        if (!FechaUtil.tieneFormatoFechaValido(comprobante.getFechaEmision()))
            throw new InvalidArgumentException("fechaEmision tiene formato inválido. Debe tener formato dd-MM-AAAA");
    }

    private static void validarEncabezadoGenerico(Encabezado encabezado, String fechaEmision) {
        required(encabezado, "El encabezado es obligatorio");
        required(fechaEmision, "El fecha de emision es obligatorio");
        if (!FechaUtil.tieneFormatoFechaValido(fechaEmision))
            throw new InvalidArgumentException("fechaEmision tiene formato inválido. Debe tener formato dd-MM-AAAA");

        validarDocEncabezadoGenerico(encabezado.getDocEncabezado(), fechaEmision);
        validarEmisorEncabezadoGenerico(encabezado.getEmisorEncabezado());
        validarCompradorEncabezadoGenerico(encabezado.getCompradorEncabezado());
        validarInformacionAdicionalEncabezadoGenerico(encabezado.getInformacionAdicionalEncabezado());
        validarTransporteEncabezadoGenerico(encabezado.getTransporteEncabezado());
        validarTotalesEncabezadoGenerico(encabezado.getTotalesEncabezado());
        validarOtraMonedaEncabezadoGenerico(encabezado.getOtraMonedaEncabezado());
    }

    private static void validarDocEncabezadoGenerico(DocEncabezado doc, String fechaEmision) {
        required(doc, "El doc del encabezado es obligatorio");
        required(fechaEmision, "La fecha de emisión es obligatoria");
        required(fechaEmision, "El fecha de emision es obligatorio");
        if (!FechaUtil.tieneFormatoFechaValido(fechaEmision))
            throw new InvalidArgumentException("fechaEmision tiene formato inválido. Debe tener formato dd-MM-AAAA");

    }

    private static void validarEmisorEncabezadoGenerico(EmisorEncabezado emisor) {
        required(emisor, "El emisor es obligatorio");
        required(emisor.getRnc(), "El RNC del emisor es obligatorio");
        required(emisor.getRazonSocial(), "La razón social del emisor es obligatoria");
    }

    private static void validarCompradorEncabezadoGenerico(CompradorEncabezado comprador) {
    }

    private static void validarInformacionAdicionalEncabezadoGenerico(InformacionAdicionalEncabezado info) {
    }

    private static void validarTransporteEncabezadoGenerico(TransporteEncabezado transporte) {
    }

    private static void validarTotalesEncabezadoGenerico(TotalesEncabezado totales) {
    }

    private static void validarOtraMonedaEncabezadoGenerico(OtraMonedaEncabezado otraMoneda) {
    }

    private static void validarItemsGenerico(List<Item> itemList) {
        required(itemList, "Debe existir al menos un ítem en el comprobante");
    }

    private static void validarSubtotalesGenerico(List<SubTotal> subTotalList) {
    }

    private static void validarDescuentosORecargosGenerico(List<DescuentoORecargo> descuentoORecargoList) {
    }

    private static void validarPaginasGenerico(List<PaginaSubTotal> paginaSubTotalList) {
    }

    private static void validarInformacionReferenciaGenerico(InformacionReferencia informacionReferencia) {
    }
}
