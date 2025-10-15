package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.DescuentoORecargo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.InformacionReferencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.PaginaSubTotal;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class ComprobanteXmlMapper {

    public ComprobanteXml toXMLExtendido(Comprobante comprobante) {
        return ComprobanteXml.builder()
                .encabezado(this.mapEncabezado(comprobante.encabezado()))
                .detallesItems(this.mapDetalles(comprobante.items()))
                .subTotales(null) // No se maneja por el momento, ademas que es opcional
                .descuentosORecargos(this.mapDescuentoORecargo(comprobante.descuentosORecargos()))
                .paginas(this.mapPaginas(comprobante.paginas()))
                .informacionReferencia(this.mapInformacionReferencia(comprobante.informacionReferencia()))
                .build();

    }

    /* Builder de Encabezado */

    private EncabezadoXml mapEncabezado(Encabezado encabezado) {
        if (encabezado == null) return null;
        return EncabezadoXml.builder()
                .version(encabezado.version())
                .idDoc(this.mapDocEncabezado(encabezado.docEncabezado()))
                .emisor(this.mapEmisorEncabezado(encabezado.emisorEncabezado()))
                .comprador(this.mapCompradorEncabezado(encabezado.compradorEncabezado()))
                .informacionAdicional(null) // Es opcional, casi nunca se usa.
                .transporte(this.mapTransporte(encabezado.transporteEncabezado()))
                .totales(this.mapTotales(encabezado.totalesEncabezado()))
                .otraMoneda(this.mapOtraMoneda(encabezado.otraMonedaEncabezado()))
                .build();
    }

    private DocXml mapDocEncabezado(DocEncabezado docEncabezado) {
        if (docEncabezado == null) return null;
        return DocXml.builder()
                .tipoComprobante(docEncabezado.tipoComprobante().getValor())
                .eNCF(docEncabezado.secuencia()) // FALTAAAAAAA
                .fechaVencimientoSecuencia(docEncabezado.fechaVencimientoSecuencia())
                .indicadorNotaCredito(docEncabezado.indicadorNotaCredito())
                .indicadorEnvioDiferido(docEncabezado.indicadorEnvioDiferido())
                .indicadorMontoGravado(docEncabezado.indicadorMontoGravado())
                .tipoIngreso(docEncabezado.tipoIngreso())
                .tipoPago(docEncabezado.tipoPago())
                .fechaLimitePago(docEncabezado.fechaLimitePago())
                .terminoPago(docEncabezado.terminoPago())
                .tablaFormasPago(this.mapFormasPago(docEncabezado.tablaFormasPago()))
                .tipoCuentaPago(docEncabezado.tipoCuentaPago())
                .numeroCuentaPago(docEncabezado.numeroCuentaPago())
                .bancoPago(docEncabezado.bancoPago())
                .fechaDesde(docEncabezado.fechaDesde())
                .fechaHasta(docEncabezado.fechaHasta())
                .totalPaginas(docEncabezado.totalPaginas())
                .build();

    }

    private EmisorXml mapEmisorEncabezado(EmisorEncabezado emisorEncabezado) {
        if (emisorEncabezado == null) return null;
        return EmisorXml.builder()
                .rnc(emisorEncabezado.rnc())
                .razonSocial(emisorEncabezado.razonSocial())
                .nombreComercial(emisorEncabezado.nombreComercial())
                .sucursal(emisorEncabezado.sucursal())
                .direccionEmisor(emisorEncabezado.direccionEmisor())
                .municipio(emisorEncabezado.municipio())
                .provincia(emisorEncabezado.provincia())
                .correoEmisor(emisorEncabezado.correoEmisor())
                .sitioWeb(emisorEncabezado.sitioWeb())
                .actividadEconomica(emisorEncabezado.actividadEconomica())
                .codigoVendedor(emisorEncabezado.codigoVendedor())
                .numeroFacturaInterna(emisorEncabezado.numeroFacturaInterna())
                .numeroPedidoInterno(emisorEncabezado.numeroPedidoInterno())
                .zonaVenta(emisorEncabezado.zonaVenta())
                .rutaVenta(emisorEncabezado.rutaVenta())
                .informacionAdicionalEmisor(emisorEncabezado.informacionAdicionalEmisor())
                .fechaEmision(emisorEncabezado.fechaEmision())
                .build();
    }

    private CompradorXml mapCompradorEncabezado(CompradorEncabezado compradorEncabezado) {
        if (compradorEncabezado == null) return null;
        return CompradorXml.builder()
                .rnc(compradorEncabezado.rnc())
                .identificadorExtranjero(compradorEncabezado.identificadorExtranjero())
                .razonSocial(compradorEncabezado.razonSocial())
                .contacto(compradorEncabezado.contacto())
                .correo(compradorEncabezado.correo())
                .direccion(compradorEncabezado.direccion())
                .municipio(compradorEncabezado.municipio())
                .provincia(compradorEncabezado.provincia())
                .fechaEntrega(compradorEncabezado.fechaEntrega())
                .contactoEntrega(compradorEncabezado.contactoEntrega())
                .direccionEntrega(compradorEncabezado.direccionEntrega())
                .telefonoAdicionl(compradorEncabezado.telefonoAdicionl())
                .fechaOrdenCompra(compradorEncabezado.fechaOrdenCompra())
                .numeroOrdenCompra(compradorEncabezado.numeroOrdenCompra())
                .codigoInternoComprador(compradorEncabezado.codigoInternoComprador())
                .responsablePago(compradorEncabezado.responsablePago())
                .informacionAdicionalComprador(compradorEncabezado.informacionAdicionalComprador())
                .build();
    }

    private TransporteXml mapTransporte(TransporteEncabezado transporteEncabezado) {
        if (transporteEncabezado == null) return null;
        return TransporteXml.builder()
                .conductor(transporteEncabezado.conductor())
                .documentoTransporte(transporteEncabezado.documentoTransporte())
                .ficha(transporteEncabezado.ficha())
                .placa(transporteEncabezado.placa())
                .rutaTransporte(transporteEncabezado.rutaTransporte())
                .zonaTransporte(transporteEncabezado.zonaTransporte())
                .numeroAlbaran(transporteEncabezado.numeroAlbaran())
                .build();

    }

    private TotalesXml mapTotales(TotalesEncabezado totalesEncabezado) {
        if (totalesEncabezado == null) return null;
        return TotalesXml.builder()
                .montoGravadoTotal(totalesEncabezado.getMontoGravadoTotal())
                .montoGravadoI1(totalesEncabezado.getMontoGravadoI1())
                .montoGravadoI2(totalesEncabezado.getMontoGravadoI2())
                .montoGravadoI3(totalesEncabezado.getMontoGravadoI3())
                .montoExento(totalesEncabezado.getMontoExento())
                .itbs1(totalesEncabezado.getItbs1())
                .itbs2(totalesEncabezado.getItbs2())
                .itbs3(totalesEncabezado.getItbs3())
                .totalITBIS(totalesEncabezado.getTotalITBIS())
                .totalITBIS1(totalesEncabezado.getTotalITBIS1())
                .totalITBIS2(totalesEncabezado.getTotalITBIS2())
                .totalITBIS3(totalesEncabezado.getTotalITBIS3())
                .montoImpuestoAdicional(totalesEncabezado.getMontoImpuestoAdicional())
                .impuestosAdicionales(this.mapImpuestosAdicionales(totalesEncabezado.getImpuestosAdicionales()))
                .montoTotal(totalesEncabezado.getMontoTotal())
                .montoNoFacturable(totalesEncabezado.getMontoNoFacturable())
                .montoPeriodo(totalesEncabezado.getMontoPeriodo())
                .saldoAnterior(totalesEncabezado.getSaldoAnterior())
                .montoAvancePago(totalesEncabezado.getMontoAvancePago())
                .valorPagar(totalesEncabezado.getValorPagar())
                .totalITBISRetenido(totalesEncabezado.getTotalITBISRetenido())
                .totalISRRetencion(totalesEncabezado.getTotalISRRetencion())
                .totalITBISPercepcion(totalesEncabezado.getTotalITBISPercepcion())
                .totalISRPercepcion(totalesEncabezado.getTotalISRPercepcion())
                .build();
    }

    private OtraMonedaXml mapOtraMoneda(OtraMonedaEncabezado otraMonedaEncabezado) {
        if (otraMonedaEncabezado == null) return null;
        return OtraMonedaXml.builder()
                .tipoMoneda(otraMonedaEncabezado.getTipoMoneda())
                .tipoCambio(otraMonedaEncabezado.getTipoCambio())
                .montoGravadoTotalOtraMoneda(otraMonedaEncabezado.getMontoGravadoTotalOtraMoneda())
                .montoGravado1OtraMoneda(otraMonedaEncabezado.getMontoGravado1OtraMoneda())
                .montoGravado2OtraMoneda(otraMonedaEncabezado.getMontoGravado2OtraMoneda())
                .montoGravado3OtraMoneda(otraMonedaEncabezado.getMontoGravado3OtraMoneda())
                .montoExentoOtraMoneda(otraMonedaEncabezado.getMontoExentoOtraMoneda())
                .totalITBISOtraMoneda(otraMonedaEncabezado.getTotalITBISOtraMoneda())
                .totalITBIS1OtraMoneda(otraMonedaEncabezado.getTotalITBIS1OtraMoneda())
                .totalITBIS2OtraMoneda(otraMonedaEncabezado.getTotalITBIS2OtraMoneda())
                .totalITBIS3OtraMoneda(otraMonedaEncabezado.getTotalITBIS3OtraMoneda())
                .montoImpuestoAdicionalOtraMoneda(otraMonedaEncabezado.getMontoImpuestoAdicionalOtraMoneda())
                .listImpuestoAdicionalOtraMoneda(this.mapImpuestosAdicionalesOtraMoneda(otraMonedaEncabezado.getListImpuestoAdicionalOtraMoneda()))
                .montoTotalOtraMoneda(otraMonedaEncabezado.getMontoTotalOtraMoneda())
                .build();
    }

    private List<FormaPagoXml> mapFormasPago(List<FormaPago> formaPagoList) {
        if (formaPagoList == null || formaPagoList.isEmpty()) return null;
        return !isNull(formaPagoList) ? formaPagoList.stream().map(this::mapFormaPago).collect(Collectors.toList()) : null;
    }

    private FormaPagoXml mapFormaPago(FormaPago formaPago) {
        if (formaPago == null) return null;
        return FormaPagoXml.builder()
                .formaPago(formaPago.getTipo())
                .montoPago(formaPago.getMonto())
                .build();
    }

    private List<ImpuestoAdicionalXml> mapImpuestosAdicionales(List<TotalesEncabezado.ImpuestoAdicional> impuestoAdicionalList) {
        if (impuestoAdicionalList == null || impuestoAdicionalList.isEmpty()) return null;
        return impuestoAdicionalList.stream().map(this::mapItemAdicional).collect(Collectors.toList());
    }

    private ImpuestoAdicionalXml mapItemAdicional(TotalesEncabezado.ImpuestoAdicional impuestoAdicional) {
        if (impuestoAdicional == null) return null;
        return ImpuestoAdicionalXml.builder()
                .tipoImpuesto(impuestoAdicional.getTipoImpuesto())
                .tasaImpuestoAdicional(impuestoAdicional.getTasaImpuestoAdicional())
                .montoImpuestoSelectivoConsumoEspecifico(impuestoAdicional.getMontoImpuestoSelectivoConsumoEspecifico())
                .montoImpuestoSelectivoConsumoAdvalorem(impuestoAdicional.getMontoImpuestoSelectivoConsumoAdvalorem())
                .otrosImpuestosAdicionales(impuestoAdicional.getOtrosImpuestosAdicionales())
                .build();
    }

    private List<ImpuestoAdicionalOtraMonedaXml> mapImpuestosAdicionalesOtraMoneda(List<OtraMonedaEncabezado.ImpuestoAdicionalOtraMoneda> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapImpuestosAdicionalesOtraMoneda).collect(Collectors.toList());
    }

    private ImpuestoAdicionalOtraMonedaXml mapImpuestosAdicionalesOtraMoneda(OtraMonedaEncabezado.ImpuestoAdicionalOtraMoneda impuestoAdiconalOtraMoneda) {
        if (impuestoAdiconalOtraMoneda == null) return null;
        return ImpuestoAdicionalOtraMonedaXml.builder()
                .tipoImpuestoOtraMoneda(impuestoAdiconalOtraMoneda.getTipoImpuestoOtraMoneda())
                .tasaImpuestoAdicionalOtraMoneda(impuestoAdiconalOtraMoneda.getTasaImpuestoAdicionalOtraMoneda())
                .montoImpuestoSelectivoConsumoEspecificoOtraMoneda(impuestoAdiconalOtraMoneda.getMontoImpuestoSelectivoConsumoEspecificoOtraMoneda())
                .montoImpuestoSelectivoConsumoAdvaloremOtraMoneda(impuestoAdiconalOtraMoneda.getMontoImpuestoSelectivoConsumoAdvaloremOtraMoneda())
                .otrosImpuestosAdicionalesOtraMoneda(impuestoAdiconalOtraMoneda.getOtrosImpuestosAdicionalesOtraMoneda())
                .build();
    }

    /* Builder de Items */

    private List<LineaComprobanteXml> mapDetalles(List<Item> itemList) {
        if (itemList == null || itemList.isEmpty()) return null;
        return itemList.stream().map(this::mapDetallesItem).collect(Collectors.toList());
    }

    private LineaComprobanteXml mapDetallesItem(Item item) {
        return LineaComprobanteXml.builder()
                .numeroLinea(item.getNumeroLinea())
                .codigosItem(this.mapCodigosItem(item.getCodigosItem()))
                .indicadorFacturacion(item.getIndicadorFacturacion())
                .retencion(this.mapRetencionItem(item.getRetencionItem()))
                .nombreItem(item.getDescripcionItem())
                .indicadorBienoServicio(item.getIndicadorBienoServicio())
                .descripcionItem(item.getDescripcionItem())
                .cantidadItem(item.getCantidadItem())
                .unidadMedida(item.getUnidadMedida())
                .cantidadReferencia(item.getCantidadReferencia())
                .unidadReferencia(item.getUnidadReferencia())
                .tablaSubcantidad(this.mapSubCantidadesItem(item.getTablaSubcantidad()))
                .gradosAlcohol(item.getGradosAlcohol())
                .precioUnitarioReferencia(item.getPrecioUnitarioReferencia())
                .fechaElaboracion(item.getFechaElaboracion())
                .fechaVencimiento(item.getFechaVencimiento())
                .precioUnitario(item.getPrecioUnitario())
                .descuentoMonto(item.getDescuentoMonto())
                .tablaSubDescuento(this.mapSubDescuentosItem(item.getTablaSubDescuentoItem()))
                .recargoMonto(item.getRecargoMonto())
                .tablaSubRecargo(this.mapSubRecargosItem(item.getTablaSubRecargoItem()))
                .tablaImpuestoAdicional(this.mapImpuestoAdicionalItem(item.getTablaImpuestoAdicionalItem()))
                .otraMonedaDetalle(this.mapOtraMonedaDescuentoItem(item.getOtraMonedaDescuentoItem()))
                .montoItem(item.getMontoItem())
                .build();
    }

    private List<CodigoItemXml> mapCodigosItem (List<CodigoItem> codigoItemList) {
        if (codigoItemList == null || codigoItemList.isEmpty()) return null;
        return codigoItemList.stream().map(this::mapCodificacionItem).collect(Collectors.toList());
    }

    private CodigoItemXml mapCodificacionItem (CodigoItem codigoItem) {
        if(codigoItem == null) return null;
        return CodigoItemXml.builder()
                .tipoCodigo(codigoItem.tipoCodigo())
                .codigoItem(codigoItem.codigoItem())
                .build();
    }

    private RetencionXml mapRetencionItem(RetencionItem retencionItem) {
        if (retencionItem == null) return null;
        return RetencionXml.builder()
                .indAgenteRetencionPercepcion(retencionItem.getIndAgenteRetencionPercepcion())
                .montoITBSRetenido(retencionItem.getMontoITBSRetenido())
                .montoISRRetenido(retencionItem.getMontoISRRetenido())
                .build();
    }

    private List<SubCantidadItemXml> mapSubCantidadesItem(List<SubCantidadItem> subCantidadItemList) {
        if (subCantidadItemList == null || subCantidadItemList.isEmpty()) return null;
        return subCantidadItemList.stream().map(this::mapSubCantidadItem).collect(Collectors.toList());
    }

    private SubCantidadItemXml mapSubCantidadItem(SubCantidadItem subcantidadRequestDto) {
        if (subcantidadRequestDto == null) return null;
        return SubCantidadItemXml.builder()
                .codigoSubcantidad(subcantidadRequestDto.getCodigoSubcantidad())
                .subCantidad(subcantidadRequestDto.getSubCantidad())
                .build();
    }

    private List<SubDescuentoXml> mapSubDescuentosItem(List<SubDescuentoItem> subDescuentoItemList) {
        if (subDescuentoItemList == null || subDescuentoItemList.isEmpty()) return null;
        return subDescuentoItemList.stream().map(this::mapSubDescuentoItem).collect(Collectors.toList());
    }

    private SubDescuentoXml mapSubDescuentoItem(SubDescuentoItem subDescuentoItemList) {
        if (subDescuentoItemList == null) return null;
        return SubDescuentoXml.builder()
                .tipoSubDescuento(subDescuentoItemList.getTipoSubDescuento())
                .subDescuentoPorcentaje(subDescuentoItemList.getSubDescuentoPorcentaje())
                .montoSubDescuento(subDescuentoItemList.getMontoSubDescuento())
                .build();
    }

    private List<SubRecargoXml> mapSubRecargosItem(List<SubRecargoItem> subRecargoItemList) {
        if (subRecargoItemList == null || subRecargoItemList.isEmpty()) return null;
        return subRecargoItemList.stream().map(this::mapSubRecargoItem).collect(Collectors.toList());
    }

    private SubRecargoXml mapSubRecargoItem(SubRecargoItem subRecargoItem) {
        if (subRecargoItem == null) return null;
        return SubRecargoXml.builder()
                .tipoSubRecargo(subRecargoItem.getTipoSubRecargo())
                .subRecargoPorcentaje(subRecargoItem.getSubRecargoPorcentaje())
                .montoSubRecargo(subRecargoItem.getMontoSubRecargo())
                .build();
    }

    private List<ImpuestoAdicionalDetalleXml> mapImpuestoAdicionalItem(List<ImpuestoAdicionalItem> impuestoAdicionalItems) {
        if (impuestoAdicionalItems == null || impuestoAdicionalItems.isEmpty()) return null;
        return impuestoAdicionalItems.stream().map(
                item -> ImpuestoAdicionalDetalleXml.builder().tipoImpuestoAdicional(item.tipoImpuestoAdicional()).build()
        ).collect(Collectors.toList());
    }

    private OtraMonedaDescuentoXml mapOtraMonedaDescuentoItem(OtraMonedaDescuentoItem otraMonedaDescuentoItem) {
        if (otraMonedaDescuentoItem == null) return null;
        return OtraMonedaDescuentoXml.builder()
                .precioOtraMoneda(otraMonedaDescuentoItem.getPrecioOtraMoneda())
                .descuentoOtraMoneda(otraMonedaDescuentoItem.getDescuentoOtraMoneda())
                .recargoOtraMoneda(otraMonedaDescuentoItem.getRecargoOtraMoneda())
                .montoItemOtraMoneda(otraMonedaDescuentoItem.getMontoItemOtraMoneda())
                .build();
    }

    /* Builder descuentosoRecargos */

    private List<DescuentoORecargoXml> mapDescuentoORecargo(List<DescuentoORecargo> descuentoORecargoList) {
        if (descuentoORecargoList == null || descuentoORecargoList.isEmpty()) return null;
        return descuentoORecargoList.stream().map(this::mapDescuentoORecargo).collect(Collectors.toList());
    }

    private DescuentoORecargoXml mapDescuentoORecargo(DescuentoORecargo descuentoORecargo) {
        if (descuentoORecargo == null) return null;
        return DescuentoORecargoXml.builder()
                .numeroLinea(descuentoORecargo.numeroLinea())
                .tipoAjuste(descuentoORecargo.tipoAjuste())
                .indicadorNorma1007(descuentoORecargo.indicadorNorma1007())
                .descripcionDescuentoORecargo(descuentoORecargo.descripcionDescuentoORecargo())
                .tipoValor(descuentoORecargo.tipoValor())
                .valorDescuentoORecargo(descuentoORecargo.valorDescuentoORecargo())
                .montoDescuentoORecargo(descuentoORecargo.montoDescuentoORecargo())
                .montoDescuentooRecargoOtraMoneda(descuentoORecargo.montoDescuentooRecargoOtraMoneda())
                .indicadorFacturacionDescuentoORecargo(descuentoORecargo.indicadorFacturacionDescuentoORecargo())
                .build();
    }

    /* Builder paginas */

    private List<PaginaSubTotalXml> mapPaginas(List<PaginaSubTotal> paginaSubTotalList) {
        if (paginaSubTotalList == null || paginaSubTotalList.isEmpty()) return null;
        return paginaSubTotalList.stream().map(this::mapPagina).collect(Collectors.toList());
    }

    private PaginaSubTotalXml mapPagina(PaginaSubTotal paginaSubTotalList) {
        if (paginaSubTotalList == null) return null;
        return PaginaSubTotalXml.builder()
                .paginaNo(paginaSubTotalList.paginaNo())
                .noLineaDesde(paginaSubTotalList.noLineaDesde())
                .noLineaHasta(paginaSubTotalList.noLineaHasta())
                .subtotalMontoGravadoPagina(paginaSubTotalList.subtotalMontoGravadoPagina())
                .subtotalMontoGravado1Pagina(paginaSubTotalList.subtotalMontoGravado1Pagina())
                .subtotalMontoGravado2Pagina(paginaSubTotalList.subtotalMontoGravado2Pagina())
                .subtotalMontoGravado3Pagina(paginaSubTotalList.subtotalMontoGravado3Pagina())
                .subtotalExentoPagina(paginaSubTotalList.subtotalExentoPagina())
                .subtotalItbisPagina(paginaSubTotalList.subtotalItbisPagina())
                .subtotalItbis1Pagina(paginaSubTotalList.subtotalItbis1Pagina())
                .subtotalItbis2Pagina(paginaSubTotalList.subtotalItbis2Pagina())
                .subtotalItbis3Pagina(paginaSubTotalList.subtotalItbis3Pagina())
                .subtotalImpuestoAdicionalPagina(paginaSubTotalList.subtotalImpuestoAdicionalPagina())
                .subtotalImpuestoAdicional(this.mapSubtotalImpuestoAdicional(paginaSubTotalList.subtotalImpuestoAdicional()))
                .montoSubtotalPagina(paginaSubTotalList.montoSubtotalPagina())
                .subtotalMontoNoFacturablePagina(paginaSubTotalList.subtotalMontoNoFacturablePagina())
                .build();
    }

    private SubtotalImpuestoAdicionalXml mapSubtotalImpuestoAdicional(PaginaSubTotal.SubtotalImpuestoAdicional subtotalImpuestoAdicional) {
        if (subtotalImpuestoAdicional == null) return null;
        return SubtotalImpuestoAdicionalXml.builder()
                .subtotalOtrosImpuesto(subtotalImpuestoAdicional.subtotalOtrosImpuesto())
                .subtotalImpuestoSelectivoConsumoEspecificoPagina(subtotalImpuestoAdicional.subtotalImpuestoSelectivoConsumoEspecificoPagina())
                .build();
    }

    /* Builder informacion referencia */

    private InformacionReferenciaXml mapInformacionReferencia(InformacionReferencia informacionReferencia) {
        if (informacionReferencia == null) return null;
        return InformacionReferenciaXml.builder()
                .NCFModificado(informacionReferencia.NCFModificado())
                .RNCOtroContribuyente(informacionReferencia.RNCOtroContribuyente())
                .fechaNCFModificado(informacionReferencia.fechaNCFModificado())
                .codigoModificacion(informacionReferencia.codigoModificacion())
                .razonModificado(informacionReferencia.razonModificado())
                .build();
    }

}
