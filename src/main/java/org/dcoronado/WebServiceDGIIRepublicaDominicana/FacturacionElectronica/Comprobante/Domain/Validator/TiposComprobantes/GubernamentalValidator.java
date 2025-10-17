package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.TiposComprobantes;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorEspecificoTemplate;

import java.util.List;

public class GubernamentalValidator extends ComprobanteValidatorEspecificoTemplate {


    @Override
    protected void validarDocEncabezadoEspecifico(DocEncabezado doc, String fechaEmision) {

    }

    @Override
    protected void validarEmisorEncabezadoEspecifico(EmisorEncabezado emisor) {

    }

    @Override
    protected void validarCompradorEncabezadoEspecifico(CompradorEncabezado comprador) {

    }

    @Override
    protected void validarInformacionAdicionalEncabezadoEspecifico(InformacionAdicionalEncabezado info) {

    }

    @Override
    protected void validarTransporteEncabezadoEspecifico(TransporteEncabezado transporte) {

    }

    @Override
    protected void validarTotalesEncabezadoEspecifico(TotalesEncabezado totales) {

    }

    @Override
    protected void validarOtraMonedaEncabezadoEspecifico(OtraMonedaEncabezado otraMoneda) {

    }

    @Override
    protected void validarItemsEspecifico(List<Item> itemList) {

    }

    @Override
    protected void validarSubtotalesEspecifico(List<SubTotal> subTotalList) {

    }

    @Override
    protected void validarDescuentosORecargosEspecifico(List<DescuentoORecargo> descuentoORecargoList) {

    }

    @Override
    protected void validarPaginasEspecifico(List<PaginaSubTotal> paginaSubTotalList) {

    }

    @Override
    protected void validarInformacionReferenciaEspecifico(InformacionReferencia informacionReferencia) {

    }
}
