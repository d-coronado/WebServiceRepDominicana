package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;

import java.util.List;

@RequiredArgsConstructor
public abstract class ComprobanteValidatorEspecificoTemplate implements ComprobanteEspecificoValidator {

    @Override
    public final void execute(Comprobante comprobante) {
        // Validaciones especificas por cada parte del esqueleto que cada tipo de comprobante debe implementar
        validarEncabezadoEspecifico(comprobante.getEncabezado(),comprobante.getEncabezado().getEmisorEncabezado().getFechaEmision());
        validarItemsEspecifico(comprobante.getItems());
        validarSubtotalesEspecifico(comprobante.getSubtotales());
        validarDescuentosORecargosEspecifico(comprobante.getDescuentosORecargos());
        validarPaginasEspecifico(comprobante.getPaginas());
        validarInformacionReferenciaEspecifico(comprobante.getInformacionReferencia());
    }

    protected final void validarEncabezadoEspecifico(Encabezado encabezado, String fechaEmision) {
        validarDocEncabezadoEspecifico(encabezado.getDocEncabezado(), fechaEmision);
        validarEmisorEncabezadoEspecifico(encabezado.getEmisorEncabezado());
        validarCompradorEncabezadoEspecifico(encabezado.getCompradorEncabezado());
        validarInformacionAdicionalEncabezadoEspecifico(encabezado.getInformacionAdicionalEncabezado());
        validarTransporteEncabezadoEspecifico(encabezado.getTransporteEncabezado());
        validarTotalesEncabezadoEspecifico(encabezado.getTotalesEncabezado());
        validarOtraMonedaEncabezadoEspecifico(encabezado.getOtraMonedaEncabezado());
    }

    /* Validaciones especificas que cada hijo esta obligado a implementar */

    /* Validaciones especificas del envoltorio Encabezado */
    protected abstract void validarDocEncabezadoEspecifico(DocEncabezado doc, String fechaEmision);

    protected abstract void validarEmisorEncabezadoEspecifico(EmisorEncabezado emisor);

    protected abstract void validarCompradorEncabezadoEspecifico(CompradorEncabezado comprador);

    protected abstract void validarInformacionAdicionalEncabezadoEspecifico(InformacionAdicionalEncabezado info);

    protected abstract void validarTransporteEncabezadoEspecifico(TransporteEncabezado transporte);

    protected abstract void validarTotalesEncabezadoEspecifico(TotalesEncabezado totales);

    protected abstract void validarOtraMonedaEncabezadoEspecifico(OtraMonedaEncabezado otraMoneda);

    /* Validaciones especificas de Items */
    protected abstract void validarItemsEspecifico(List<Item> itemList);

    /* Validaciones especificas de subtotales */
    protected abstract void validarSubtotalesEspecifico(List<SubTotal> subTotalList);

    /* Validaciones especificas de descuentosorecargos */
    protected abstract void validarDescuentosORecargosEspecifico(List<DescuentoORecargo> descuentoORecargoList);

    /* Validaciones especificas de paginacion */
    protected abstract void validarPaginasEspecifico(List<PaginaSubTotal> paginaSubTotalList);

    /* validaciones especificas de informacion de referencia */
    protected abstract void validarInformacionReferenciaEspecifico(InformacionReferencia informacionReferencia);
}