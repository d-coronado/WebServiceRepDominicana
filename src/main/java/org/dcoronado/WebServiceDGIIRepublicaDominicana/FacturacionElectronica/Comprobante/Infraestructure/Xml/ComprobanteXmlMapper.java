package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.DescuentoORecargo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.InformacionReferencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.PaginaSubTotal;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.resumen.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class ComprobanteXmlMapper {

    public ComprobanteXmlExtendido toXMLExtendido(Comprobante comprobante) {
        return ComprobanteXmlExtendido.builder()
                .encabezado(this.mapEncabezado(comprobante.getEncabezado(),comprobante.getEncf()))
                .detallesItems(this.mapDetalles(comprobante.getItems()))
                .subTotales(null) // No se maneja por el momento, ademas que es opcional
                .descuentosORecargos(this.mapDescuentoORecargo(comprobante.getDescuentosORecargos()))
                .paginas(this.mapPaginas(comprobante.getPaginas()))
                .informacionReferencia(this.mapInformacionReferencia(comprobante.getInformacionReferencia()))
                .fechaHoraFirma(comprobante.getFechaHoraFirma())
                .build();

    }

    public ComprobanteResumenXml toXMLResumido(Comprobante comprobante) {
        return ComprobanteResumenXml.builder()
                .encabezado(this.mapEncabezadoResumen(comprobante.getEncabezado(),comprobante.getEncf(),comprobante.getCodigoSeguridad()))
                .build();
    }

    /* BUILDER DEL COMPROBANTE EXTENDIDO */

    /* Builder de Encabezado */

    private EncabezadoXml mapEncabezado(Encabezado encabezado, final String encf) {
        if (encabezado == null) return null;
        return EncabezadoXml.builder()
                .version(encabezado.getVersion())
                .idDoc(this.mapDocEncabezado(encabezado.getDocEncabezado(),encf))
                .emisor(this.mapEmisorEncabezado(encabezado.getEmisorEncabezado()))
                .comprador(this.mapCompradorEncabezado(encabezado.getCompradorEncabezado()))
                .informacionAdicional(null) // Es opcional, casi nunca se usa.
                .transporte(this.mapTransporte(encabezado.getTransporteEncabezado()))
                .totales(this.mapTotales(encabezado.getTotalesEncabezado()))
                .otraMoneda(this.mapOtraMoneda(encabezado.getOtraMonedaEncabezado()))
                .build();
    }

    private DocXml mapDocEncabezado(DocEncabezado docEncabezado,final String encf) {
        if (docEncabezado == null) return null;
        return DocXml.builder()
                .tipoComprobante(docEncabezado.getTipoComprobanteTributarioEnum().getValor())
                .eNCF(encf)
                .fechaVencimientoSecuencia(docEncabezado.getFechaVencimientoSecuencia())
                .indicadorNotaCredito(docEncabezado.getIndicadorNotaCredito())
                .indicadorEnvioDiferido(docEncabezado.getIndicadorEnvioDiferido())
                .indicadorMontoGravado(docEncabezado.getIndicadorMontoGravado())
                .tipoIngreso(docEncabezado.getTipoIngreso())
                .tipoPago(docEncabezado.getTipoPago())
                .fechaLimitePago(docEncabezado.getFechaLimitePago())
                .terminoPago(docEncabezado.getTerminoPago())
                .tablaFormasPago(this.mapFormasPago(docEncabezado.getTablaFormasPago()))
                .tipoCuentaPago(docEncabezado.getTipoCuentaPago())
                .numeroCuentaPago(docEncabezado.getNumeroCuentaPago())
                .bancoPago(docEncabezado.getBancoPago())
                .fechaDesde(docEncabezado.getFechaDesde())
                .fechaHasta(docEncabezado.getFechaHasta())
                .totalPaginas(docEncabezado.getTotalPaginas())
                .build();

    }

    private EmisorXml mapEmisorEncabezado(EmisorEncabezado emisorEncabezado) {
        if (emisorEncabezado == null) return null;
        return EmisorXml.builder()
                .rnc(emisorEncabezado.getRnc())
                .razonSocial(emisorEncabezado.getRazonSocial())
                .nombreComercial(emisorEncabezado.getNombreComercial())
                .sucursal(emisorEncabezado.getSucursal())
                .direccionEmisor(emisorEncabezado.getDireccionEmisor())
                .municipio(emisorEncabezado.getMunicipio())
                .provincia(emisorEncabezado.getProvincia())
                .correoEmisor(emisorEncabezado.getCorreoEmisor())
                .sitioWeb(emisorEncabezado.getSitioWeb())
                .actividadEconomica(emisorEncabezado.getActividadEconomica())
                .codigoVendedor(emisorEncabezado.getCodigoVendedor())
                .numeroFacturaInterna(emisorEncabezado.getNumeroFacturaInterna())
                .numeroPedidoInterno(emisorEncabezado.getNumeroPedidoInterno())
                .zonaVenta(emisorEncabezado.getZonaVenta())
                .rutaVenta(emisorEncabezado.getRutaVenta())
                .informacionAdicionalEmisor(emisorEncabezado.getInformacionAdicionalEmisor())
                .fechaEmision(emisorEncabezado.getFechaEmision())
                .build();
    }

    private CompradorXml mapCompradorEncabezado(CompradorEncabezado compradorEncabezado) {
        if (compradorEncabezado == null) return null;
        return CompradorXml.builder()
                .rnc(compradorEncabezado.getRnc())
                .identificadorExtranjero(compradorEncabezado.getIdentificadorExtranjero())
                .razonSocial(compradorEncabezado.getRazonSocial())
                .contacto(compradorEncabezado.getContacto())
                .correo(compradorEncabezado.getCorreo())
                .direccion(compradorEncabezado.getDireccion())
                .municipio(compradorEncabezado.getMunicipio())
                .provincia(compradorEncabezado.getProvincia())
                .fechaEntrega(compradorEncabezado.getFechaEntrega())
                .contactoEntrega(compradorEncabezado.getContactoEntrega())
                .direccionEntrega(compradorEncabezado.getDireccionEntrega())
                .telefonoAdicionl(compradorEncabezado.getTelefonoAdicionl())
                .fechaOrdenCompra(compradorEncabezado.getFechaOrdenCompra())
                .numeroOrdenCompra(compradorEncabezado.getNumeroOrdenCompra())
                .codigoInternoComprador(compradorEncabezado.getCodigoInternoComprador())
                .responsablePago(compradorEncabezado.getResponsablePago())
                .informacionAdicionalComprador(compradorEncabezado.getInformacionAdicionalComprador())
                .build();
    }

    private TransporteXml mapTransporte(TransporteEncabezado transporteEncabezado) {
        if (transporteEncabezado == null) return null;
        return TransporteXml.builder()
                .conductor(transporteEncabezado.getConductor())
                .documentoTransporte(transporteEncabezado.getDocumentoTransporte())
                .ficha(transporteEncabezado.getFicha())
                .placa(transporteEncabezado.getPlaca())
                .rutaTransporte(transporteEncabezado.getRutaTransporte())
                .zonaTransporte(transporteEncabezado.getZonaTransporte())
                .numeroAlbaran(transporteEncabezado.getNumeroAlbaran())
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
                .nombreItem(item.getNombreItem())
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
                .tipoCodigo(codigoItem.getTipoCodigo())
                .codigoItem(codigoItem.getCodigoItem())
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
                .numeroLinea(descuentoORecargo.getNumeroLinea())
                .tipoAjuste(descuentoORecargo.getTipoAjuste())
                .indicadorNorma1007(descuentoORecargo.getIndicadorNorma1007())
                .descripcionDescuentoORecargo(descuentoORecargo.getDescripcionDescuentoORecargo())
                .tipoValor(descuentoORecargo.getTipoValor())
                .valorDescuentoORecargo(descuentoORecargo.getValorDescuentoORecargo())
                .montoDescuentoORecargo(descuentoORecargo.getMontoDescuentoORecargo())
                .montoDescuentooRecargoOtraMoneda(descuentoORecargo.getMontoDescuentooRecargoOtraMoneda())
                .indicadorFacturacionDescuentoORecargo(descuentoORecargo.getIndicadorFacturacionDescuentoORecargo())
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
                .paginaNo(paginaSubTotalList.getPaginaNo())
                .noLineaDesde(paginaSubTotalList.getNoLineaDesde())
                .noLineaHasta(paginaSubTotalList.getNoLineaHasta())
                .subtotalMontoGravadoPagina(paginaSubTotalList.getSubtotalMontoGravadoPagina())
                .subtotalMontoGravado1Pagina(paginaSubTotalList.getSubtotalItbis1Pagina())
                .subtotalMontoGravado2Pagina(paginaSubTotalList.getSubtotalItbis2Pagina())
                .subtotalMontoGravado3Pagina(paginaSubTotalList.getSubtotalItbis3Pagina())
                .subtotalExentoPagina(paginaSubTotalList.getSubtotalExentoPagina())
                .subtotalItbisPagina(paginaSubTotalList.getSubtotalItbisPagina())
                .subtotalItbis1Pagina(paginaSubTotalList.getSubtotalItbis1Pagina())
                .subtotalItbis2Pagina(paginaSubTotalList.getSubtotalItbis2Pagina())
                .subtotalItbis3Pagina(paginaSubTotalList.getSubtotalItbis3Pagina())
                .subtotalImpuestoAdicionalPagina(paginaSubTotalList.getSubtotalImpuestoAdicionalPagina())
                .subtotalImpuestoAdicional(this.mapSubtotalImpuestoAdicional(paginaSubTotalList.getSubtotalImpuestoAdicional()))
                .montoSubtotalPagina(paginaSubTotalList.getMontoSubtotalPagina())
                .subtotalMontoNoFacturablePagina(paginaSubTotalList.getSubtotalMontoNoFacturablePagina())
                .build();
    }

    private SubtotalImpuestoAdicionalXml mapSubtotalImpuestoAdicional(PaginaSubTotal.SubtotalImpuestoAdicional subtotalImpuestoAdicional) {
        if (subtotalImpuestoAdicional == null) return null;
        return SubtotalImpuestoAdicionalXml.builder()
                .subtotalOtrosImpuesto(subtotalImpuestoAdicional.getSubtotalOtrosImpuesto())
                .subtotalImpuestoSelectivoConsumoEspecificoPagina(subtotalImpuestoAdicional.getSubtotalImpuestoSelectivoConsumoEspecificoPagina())
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


    /* BUILDER DEL COMPROBANTE RESUMIDO */

    private EncabezadoResumenXml mapEncabezadoResumen(Encabezado encabezado, final String encf,final String codigoSeguridad) {
        if (encabezado == null) return null;
        return EncabezadoResumenXml.builder()
                .version(encabezado.getVersion())
                .idDoc(this.mapDocEncabezadoResumen(encabezado.getDocEncabezado(),encf))
                .emisor(this.mapEmisorEncabezadoResumen(encabezado.getEmisorEncabezado()))
                .comprador(this.mapCompradorEncabezadoResumen(encabezado.getCompradorEncabezado()))
                .totales(this.mapTotalesResumen(encabezado.getTotalesEncabezado()))
                .codigoSeguridad(codigoSeguridad)
                .build();
    }

    private DocResumenXml mapDocEncabezadoResumen(DocEncabezado docEncabezado,String encf){
        if(docEncabezado == null) return null;
        return DocResumenXml.builder()
                .tipoComprobante(docEncabezado.getTipoComprobanteTributarioEnum().getValor())
                .eNCF(encf)
                .tipoIngreso(docEncabezado.getTipoIngreso())
                .tipoPago(docEncabezado.getTipoPago())
                .tablaFormasPago(this.mapFormasPago(docEncabezado.getTablaFormasPago()))
                .build();
    }

    private EmisorResumenXml mapEmisorEncabezadoResumen(EmisorEncabezado emisorEncabezado){
        if(emisorEncabezado == null) return null;
        return EmisorResumenXml.builder()
                .rnc(emisorEncabezado.getRnc())
                .razonSocial(emisorEncabezado.getRazonSocial())
                .fechaEmision(emisorEncabezado.getFechaEmision())
                .build();
    }

    private CompradorResumenXml mapCompradorEncabezadoResumen(CompradorEncabezado compradorEncabezado){
        if(compradorEncabezado == null) return null;
        return CompradorResumenXml.builder()
                .rnc(compradorEncabezado.getRnc())
                .identificadorExtranjero(compradorEncabezado.getIdentificadorExtranjero())
                .razonSocial(compradorEncabezado.getRazonSocial())
                .build();
    }

    private TotalesResumenXml mapTotalesResumen(TotalesEncabezado totalesEncabezado){
        if(totalesEncabezado == null) return null;
        return TotalesResumenXml.builder()
                .montoGravadoTotal(totalesEncabezado.getMontoGravadoTotal())
                .montoGravadoI1(totalesEncabezado.getMontoGravadoI1())
                .montoGravadoI2(totalesEncabezado.getMontoGravadoI2())
                .montoGravadoI3(totalesEncabezado.getMontoGravadoI3())
                .montoExento(totalesEncabezado.getMontoExento())
                .totalITBIS(totalesEncabezado.getTotalITBIS())
                .totalITBIS1(totalesEncabezado.getTotalITBIS1())
                .totalITBIS2(totalesEncabezado.getTotalITBIS2())
                .totalITBIS3(totalesEncabezado.getTotalITBIS3())
                .montoImpuestoAdicional(totalesEncabezado.getMontoImpuestoAdicional())
                .impuestosAdicionales(this.mapImpuestosAdicionalesResumen(totalesEncabezado.getImpuestosAdicionales()))
                .montoTotal(totalesEncabezado.getMontoTotal())
                .montoNoFacturable(totalesEncabezado.getMontoNoFacturable())
                .montoPeriodo(totalesEncabezado.getMontoPeriodo())
                .build();
    }

    private List<ResumenImpuestoAdicionalXml> mapImpuestosAdicionalesResumen(List<TotalesEncabezado.ImpuestoAdicional> impuestoAdicionalList) {
        if (impuestoAdicionalList == null || impuestoAdicionalList.isEmpty()) return null;
        return impuestoAdicionalList.stream().map(this::mapItemAdicionalResumen).collect(Collectors.toList());
    }
    

    private ResumenImpuestoAdicionalXml mapItemAdicionalResumen(TotalesEncabezado.ImpuestoAdicional impuestoAdicional) {
        if (impuestoAdicional == null) return null;
        return ResumenImpuestoAdicionalXml.builder()
                .tipoImpuesto(impuestoAdicional.getTipoImpuesto())
                .montoImpuestoSelectivoConsumoEspecifico(impuestoAdicional.getMontoImpuestoSelectivoConsumoEspecifico())
                .montoImpuestoSelectivoConsumoAdvalorem(impuestoAdicional.getMontoImpuestoSelectivoConsumoAdvalorem())
                .otrosImpuestosAdicionales(impuestoAdicional.getOtrosImpuestosAdicionales())
                .build();
    }


}
