package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;

import java.util.List;

public abstract class ComprobanteValidatorGeneric implements ComprobanteValidator {

    @Override
    public void execute(Comprobante comprobante) {
        validarEncabezado(comprobante.encabezado());
        validarItems(comprobante.items());
        validarSubtotales(comprobante.subtotales());
        validarDescuentosORecargos(comprobante.descuentosORecargos());
        validarPaginas(comprobante.paginas());
        validarInformacionReferencia(comprobante.informacionReferencia());
    }

    protected final void validarEncabezado(Encabezado encabezado) {
        validarDocEncabezado(encabezado.docEncabezado(), encabezado.emisorEncabezado().fechaEmision());
        validarCompradorEncabezado(encabezado.compradorEncabezado());
        validarInformacionAdicionalEncabezado(encabezado.informacionAdicionalEncabezado());
        validarTransporteEncabezado(encabezado.transporteEncabezado());
        validarTotalesEncabezado(encabezado.totalesEncabezado());
        validarOtraMonedaEncabezado(encabezado.otraMonedaEncabezado());
    }

    /* Validacion de Encabezado */
    protected abstract void validarDocEncabezado(DocEncabezado doc, String fechaEmision);
    protected abstract void validarCompradorEncabezado(CompradorEncabezado comprador);
    protected abstract void validarInformacionAdicionalEncabezado(InformacionAdicionalEncabezado info);
    protected abstract void validarTransporteEncabezado(TransporteEncabezado transporte);
    protected abstract void validarTotalesEncabezado(TotalesEncabezado totales);
    protected abstract void validarOtraMonedaEncabezado(OtraMonedaEncabezado otraMoneda);

    protected abstract void validarItems(List<Item> itemList);

    protected abstract void validarSubtotales(List<SubTotal> subTotalList);

    protected abstract void validarDescuentosORecargos(List<DescuentoORecargo> descuentoORecargoList);

    protected abstract void validarPaginas(List<PaginaSubTotal> paginaSubTotalList);

    protected abstract void validarInformacionReferencia(InformacionReferencia informacionReferencia);



}
