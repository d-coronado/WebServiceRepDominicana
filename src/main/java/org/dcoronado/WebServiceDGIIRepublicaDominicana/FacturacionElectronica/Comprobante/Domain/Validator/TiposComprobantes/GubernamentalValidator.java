package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.TiposComprobantes;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorGeneric;

import java.util.List;

public class GubernamentalValidator extends ComprobanteValidatorGeneric {


    @Override
    protected void validarDocEncabezado(DocEncabezado doc, String fechaEmision) {

    }

    @Override
    protected void validarCompradorEncabezado(CompradorEncabezado comprador) {

    }

    @Override
    protected void validarInformacionAdicionalEncabezado(InformacionAdicionalEncabezado info) {

    }

    @Override
    protected void validarTransporteEncabezado(TransporteEncabezado transporte) {

    }

    @Override
    protected void validarTotalesEncabezado(TotalesEncabezado totales) {

    }

    @Override
    protected void validarOtraMonedaEncabezado(OtraMonedaEncabezado otraMoneda) {

    }

    @Override
    protected void validarItems(List<Item> itemList) {

    }

    @Override
    protected void validarSubtotales(List<SubTotal> subTotalList) {

    }

    @Override
    protected void validarDescuentosORecargos(List<DescuentoORecargo> descuentoORecargoList) {

    }

    @Override
    protected void validarPaginas(List<PaginaSubTotal> paginaSubTotalList) {

    }

    @Override
    protected void validarInformacionReferencia(InformacionReferencia informacionReferencia) {

    }

}
