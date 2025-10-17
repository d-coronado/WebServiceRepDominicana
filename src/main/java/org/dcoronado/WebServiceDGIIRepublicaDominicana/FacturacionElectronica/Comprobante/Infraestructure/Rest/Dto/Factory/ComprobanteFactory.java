package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Factory;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.DescuentoORecargo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.InformacionReferencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.PaginaSubTotal;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class ComprobanteFactory {

    public Comprobante ofDto(ComprobanteGenericRequestDto request) {
        if (isNull(request)) throw new IllegalArgumentException("DTO no puede ser null");
        return Comprobante.builder()
                .ambienteEnum(request.entornoProduccion())
                .tipoComprobanteTributarioEnum(request.tipoComprobante())
                .fechaEmision(request.fechaEmision())
                .encabezado(this.mapEncabezado(request.encabezado()))
                .items(this.mapItems(request.detalles()))
                .descuentosORecargos(this.mapDescuentoORecargo(request.descuentosORecargos()))
                .paginas(this.mapPaginas(request.paginas()))
                .informacionReferencia(this.mapInformacionReferencia(request.informacionReferencia()))
                .build();

    }

    /* Builder de encabezado */

    private Encabezado mapEncabezado(EncabezadoGenericoRequestDto encabezadoRequestDto) {
        if (encabezadoRequestDto == null) return null;
        return Encabezado.builder()
                .docEncabezado(this.mapDocEncabezado(encabezadoRequestDto.doc()))
                .emisorEncabezado(this.mapEmisorEncabezado(encabezadoRequestDto.emisor()))
                .compradorEncabezado(this.mapCompradorEncabezado(encabezadoRequestDto.comprador()))
                .informacionAdicionalEncabezado(null) // Es opcional, casi nunca se usa.
                .transporteEncabezado(this.mapTransporte(encabezadoRequestDto.transporte()))
                .totalesEncabezado(this.mapTotales(encabezadoRequestDto.totales()))
                .otraMonedaEncabezado(this.mapOtraMoneda(encabezadoRequestDto.otraMoneda()))
                .build();
    }

    private DocEncabezado mapDocEncabezado(DocRequestDto docRequestDto) {
        if (docRequestDto == null) return null;
        return DocEncabezado.builder()
                .secuencia(docRequestDto.secuencia())
                .fechaVencimientoSecuencia(docRequestDto.fechaVencimiento())
                .indicadorNotaCredito(docRequestDto.indicadorNotaCredito())
                .indicadorEnvioDiferido(docRequestDto.indicadorEnvioDiferido())
                .indicadorMontoGravado(docRequestDto.indicadorMontoGravado())
                .tipoIngreso(docRequestDto.tipoIngreso())
                .tipoPago(docRequestDto.tipoPago())
                .fechaLimitePago(docRequestDto.fechaLimitePago())
                .terminoPago(docRequestDto.terminoPago())
                .tablaFormasPago(this.mapFormasPago(docRequestDto.formasPago()))
                .tipoCuentaPago(docRequestDto.tipoCuentaPago())
                .numeroCuentaPago(docRequestDto.numeroCuentaPago())
                .bancoPago(docRequestDto.bancoPago())
                .fechaDesde(docRequestDto.fechaPeriodoServicioDesde())
                .fechaHasta(docRequestDto.fechaPeriodoServicioHasta())
                .totalPaginas(docRequestDto.totalPaginas())
                .build();

    }

    private EmisorEncabezado mapEmisorEncabezado(EmisorRequestDto emisorRequestDto) {
        if (emisorRequestDto == null) return null;
        return EmisorEncabezado.builder()
                .rnc(emisorRequestDto.rnc())
                .razonSocial(emisorRequestDto.razonSocial())
                .nombreComercial(emisorRequestDto.nombreComercial())
                .sucursal(emisorRequestDto.sucursal())
                .direccionEmisor(emisorRequestDto.direccion())
                .municipio(emisorRequestDto.municipio())
                .provincia(emisorRequestDto.provincia())
                .correoEmisor(emisorRequestDto.correo())
                .sitioWeb(emisorRequestDto.website())
                .actividadEconomica(emisorRequestDto.actividadEconomica())
                .codigoVendedor(emisorRequestDto.codigoVendedor())
                .numeroFacturaInterna(emisorRequestDto.numeroFacturaInterna())
                .numeroPedidoInterno(emisorRequestDto.numeroPedidoInterno())
                .zonaVenta(emisorRequestDto.zonaVenta())
                .rutaVenta(emisorRequestDto.rutaVenta())
                .informacionAdicionalEmisor(emisorRequestDto.informacionAdicional())
                .build();
    }

    private CompradorEncabezado mapCompradorEncabezado(CompradorRequestDto compradorRequestDto) {
        if (compradorRequestDto == null) return null;
        return CompradorEncabezado.builder()
                .rnc(compradorRequestDto.rnc())
                .identificadorExtranjero(compradorRequestDto.identificadorExtranjero())
                .razonSocial(compradorRequestDto.razonSocial())
                .contacto(compradorRequestDto.contactoNombre())
                .correo(compradorRequestDto.correoElectronico())
                .direccion(compradorRequestDto.direccion())
                .municipio(compradorRequestDto.municipio())
                .provincia(compradorRequestDto.provincia())
                .fechaEntrega(compradorRequestDto.fechaEntrega())
                .contactoEntrega(compradorRequestDto.nombreContactoEntrega())
                .direccionEntrega(compradorRequestDto.direccionEntrega())
                .telefonoAdicionl(compradorRequestDto.telefonoContactoEntrega())
                .fechaOrdenCompra(compradorRequestDto.fechaOrdenCompra())
                .numeroOrdenCompra(compradorRequestDto.numeroOrdenCompra())
                .codigoInternoComprador(compradorRequestDto.codigoInternoComprador())
                .responsablePago(compradorRequestDto.responsablePago())
                .informacionAdicionalComprador(compradorRequestDto.informacionAdicionalComprador())
                .build();
    }

    private TransporteEncabezado mapTransporte(TransporteRequestDto transporteRequestDto) {
        if (transporteRequestDto == null) return null;
        return TransporteEncabezado.builder()
                .conductor(transporteRequestDto.conductor())
                .documentoTransporte(transporteRequestDto.documentoTransporte())
                .ficha(transporteRequestDto.ficha())
                .placa(transporteRequestDto.placa())
                .rutaTransporte(transporteRequestDto.rutaTransporte())
                .zonaTransporte(transporteRequestDto.zonaTransporte())
                .numeroAlbaran(transporteRequestDto.numeroAlbaran())
                .build();

    }

    private TotalesEncabezado mapTotales(TotalesRequestDto totalesRequestDto) {
        if (totalesRequestDto == null) return null;
        return TotalesEncabezado.builder()
                .montoGravadoTotal(totalesRequestDto.montoGravadoTotal())
                .montoGravadoI1(totalesRequestDto.montoGravadoITBIS1())
                .montoGravadoI2(totalesRequestDto.montoGravadoITBIS2())
                .montoGravadoI3(totalesRequestDto.montoGravadoITBIS3())
                .montoExento(totalesRequestDto.montoExento())
                .itbs1(totalesRequestDto.tasaITBIS1())
                .itbs2(totalesRequestDto.tasaITBIS2())
                .itbs3(totalesRequestDto.tasaITBIS3())
                .totalITBIS(totalesRequestDto.totalITBIS())
                .totalITBIS1(totalesRequestDto.totalITBISTasa1())
                .totalITBIS2(totalesRequestDto.totalITBISTasa3())
                .totalITBIS3(totalesRequestDto.totalITBISTasa3())
                .montoImpuestoAdicional(totalesRequestDto.montoImpuestoAdicional())
                .impuestosAdicionales(this.mapImpuestosAdicionales(totalesRequestDto.impuestosAdicionales()))
                .montoTotal(totalesRequestDto.montoTotal())
                .montoNoFacturable(totalesRequestDto.montoNoFacturable())
                .montoPeriodo(totalesRequestDto.montoPeriodo())
                .saldoAnterior(totalesRequestDto.saldoAnteriorCobro())
                .montoAvancePago(totalesRequestDto.montoAvancePago())
                .valorPagar(totalesRequestDto.valorAPagar())
                .totalITBISRetenido(totalesRequestDto.montoTotalITBISRetenido())
                .totalISRRetencion(totalesRequestDto.montoTotalRetencionRenta())
                .totalITBISPercepcion(totalesRequestDto.montoTotalITBISPercepcion())
                .totalISRPercepcion(totalesRequestDto.montoTotalPercepcionRenta())
                .build();
    }

    private OtraMonedaEncabezado mapOtraMoneda(OtraMonedaRequestDto otraMonedaRequestDto) {
        if (otraMonedaRequestDto == null) return null;
        return OtraMonedaEncabezado.builder()
                .tipoMoneda(otraMonedaRequestDto.codigoMoneda())
                .tipoCambio(otraMonedaRequestDto.tipoCambio())
                .montoGravadoTotalOtraMoneda(otraMonedaRequestDto.montoGravadoTotal())
                .montoGravado1OtraMoneda(otraMonedaRequestDto.montoGravadoITBISTasa1())
                .montoGravado2OtraMoneda(otraMonedaRequestDto.montoGravadoITBISTasa2())
                .montoGravado3OtraMoneda(otraMonedaRequestDto.montoGravadoITBISTasa3())
                .montoExentoOtraMoneda(otraMonedaRequestDto.montoExento())
                .totalITBISOtraMoneda(otraMonedaRequestDto.totalITBIS())
                .totalITBIS1OtraMoneda(otraMonedaRequestDto.totalITBISTasa1())
                .totalITBIS2OtraMoneda(otraMonedaRequestDto.totalITBISTasa2())
                .totalITBIS3OtraMoneda(otraMonedaRequestDto.totalITBISTasa3())
                .montoImpuestoAdicionalOtraMoneda(otraMonedaRequestDto.montoImpuestoAdicional())
                .listImpuestoAdicionalOtraMoneda(this.mapImpuestosAdicionalesOtraMoneda(otraMonedaRequestDto.impuestosAdicionales()))
                .montoTotalOtraMoneda(otraMonedaRequestDto.montoTotal())
                .build();
    }

    private List<FormaPago> mapFormasPago(List<FormaPagoRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;

        return lista.stream()
                .map(this::mapFormaPago)
                .collect(Collectors.toList());
    }

    private FormaPago mapFormaPago(FormaPagoRequestDto request) {
        if (request == null) return null;
        return FormaPago.builder()
                .tipo(request.formapago())
                .monto(request.monto())
                .build();
    }

    private List<TotalesEncabezado.ImpuestoAdicional> mapImpuestosAdicionales(List<ImpuestoAdicionalRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapItemAdicional).collect(Collectors.toList());
    }

    private TotalesEncabezado.ImpuestoAdicional mapItemAdicional(ImpuestoAdicionalRequestDto request) {
        if (request == null) return null;
        return TotalesEncabezado.ImpuestoAdicional.builder()
                .tipoImpuesto(request.codigoImpuesto())
                .tasaImpuestoAdicional(request.tasaImpuesto())
                .montoImpuestoSelectivoConsumoEspecifico(request.montoImpuestoSelectivoConsumoEspecifico())
                .montoImpuestoSelectivoConsumoAdvalorem(request.montoImpuestoSelectivoConsumoAdvalorem())
                .otrosImpuestosAdicionales(request.otrosImpuestosAdicionales())
                .build();
    }

    private List<OtraMonedaEncabezado.ImpuestoAdicionalOtraMoneda> mapImpuestosAdicionalesOtraMoneda(List<ImpuestoAdicionalRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapImpuestosAdicionalesOtraMoneda).collect(Collectors.toList());
    }

    private OtraMonedaEncabezado.ImpuestoAdicionalOtraMoneda mapImpuestosAdicionalesOtraMoneda(ImpuestoAdicionalRequestDto impuestoAdicionalRequestDto) {
        if (impuestoAdicionalRequestDto == null) return null;
        return OtraMonedaEncabezado.ImpuestoAdicionalOtraMoneda.builder()
                .tipoImpuestoOtraMoneda(impuestoAdicionalRequestDto.codigoImpuesto())
                .tasaImpuestoAdicionalOtraMoneda(impuestoAdicionalRequestDto.tasaImpuesto())
                .montoImpuestoSelectivoConsumoEspecificoOtraMoneda(impuestoAdicionalRequestDto.montoImpuestoSelectivoConsumoEspecifico())
                .montoImpuestoSelectivoConsumoAdvaloremOtraMoneda(impuestoAdicionalRequestDto.montoImpuestoSelectivoConsumoAdvalorem())
                .otrosImpuestosAdicionalesOtraMoneda(impuestoAdicionalRequestDto.otrosImpuestosAdicionales())
                .build();
    }

    /* Builder de items */

    private List<Item> mapItems(List<DetalleGenericoRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapItem).collect(Collectors.toList());
    }

    private Item mapItem(DetalleGenericoRequestDto itemRequestDto) {
        if (itemRequestDto == null) return null;
        return Item.builder()
                .numeroLinea(itemRequestDto.nroLineaItem())
                .codigosItem(this.mapCodificacionesItem(itemRequestDto.codificacionesItem()))
                .indicadorFacturacion(itemRequestDto.indicadorFacturacion())
                .retencionItem(this.mapRetencionItem(itemRequestDto.retencion()))
                .nombreItem(itemRequestDto.descripcionItem())
                .indicadorBienoServicio(itemRequestDto.indicadorBienServicio())
                .descripcionItem(itemRequestDto.descrpcionAdicionalItem())
                .cantidadItem(itemRequestDto.cantidadItem())
                .unidadMedida(itemRequestDto.unidadMedida())
                .cantidadReferencia(itemRequestDto.cantidadEmpaqueReferenciaISC())
                .unidadReferencia(itemRequestDto.unidadEmpaqueReferenciaISC())
                .tablaSubcantidad(this.mapSubCantidadesItem(itemRequestDto.distribucionSubCantidades()))
                .gradosAlcohol(itemRequestDto.gradosAlcohol())
                .precioUnitarioReferencia(itemRequestDto.precioUnitarioReferencia())
                .fechaElaboracion(itemRequestDto.fechaElaboracion())
                .fechaVencimiento(itemRequestDto.fechaVencimiento())
                .precioUnitario(itemRequestDto.precioUnitarioItem())
                .descuentoMonto(itemRequestDto.montoTotalDescuento())
                .tablaSubDescuentoItem(this.mapSubDescuentosItem(itemRequestDto.subDescuentos()))
                .recargoMonto(itemRequestDto.montoTotalRecargo())
                .tablaSubRecargoItem(this.mapSubRecargosItem(itemRequestDto.subRecargos()))
                .tablaImpuestoAdicionalItem(this.mapImpuestoAdicionalItem(itemRequestDto.tiposImpuestosAdicionales()))
                .otraMonedaDescuentoItem(this.mapOtraMonedaDescuentoItem(itemRequestDto.otraMonedaDetale()))
                .montoItem(itemRequestDto.montoItemLinea())
                .build();
    }

    private List<CodigoItem> mapCodificacionesItem(List<CodificacionGenericRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapCodificacionItem).collect(Collectors.toList());
    }

    private CodigoItem mapCodificacionItem(CodificacionGenericRequestDto request) {
        if (request == null) return null;
        return CodigoItem.builder()
                .tipoCodigo(request.tipoCodigo())
                .codigoItem(request.codigoItem())
                .build();
    }

    private RetencionItem mapRetencionItem(RetencionRequestDto retencionRequestDto) {
        if (retencionRequestDto == null) return null;
        return RetencionItem.builder()
                .indAgenteRetencionPercepcion(retencionRequestDto.indicadorRetencionPercepcion())
                .montoITBSRetenido(retencionRequestDto.montoITBISRetenido())
                .montoISRRetenido(retencionRequestDto.montoRetencionRenta())
                .build();
    }

    private List<SubCantidadItem> mapSubCantidadesItem(List<SubCantidadRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapSubCantidadItem).collect(Collectors.toList());
    }

    private SubCantidadItem mapSubCantidadItem(SubCantidadRequestDto subcantidadRequestDto) {
        if (subcantidadRequestDto == null) return null;
        return SubCantidadItem.builder()
                .codigoSubcantidad(subcantidadRequestDto.codigo())
                .subCantidad(subcantidadRequestDto.subcantidad())
                .build();
    }

    private List<SubDescuentoItem> mapSubDescuentosItem(List<SubDescuentoRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapSubDescuentoItem).collect(Collectors.toList());
    }

    private SubDescuentoItem mapSubDescuentoItem(SubDescuentoRequestDto subdescuentoRequestDto) {
        if (subdescuentoRequestDto == null) return null;
        return SubDescuentoItem.builder()
                .tipoSubDescuento(subdescuentoRequestDto.tipo())
                .subDescuentoPorcentaje(subdescuentoRequestDto.subDescuentoPorcentaje())
                .montoSubDescuento(subdescuentoRequestDto.montoSubdescuento())
                .build();
    }

    private List<SubRecargoItem> mapSubRecargosItem(List<SubRecargoRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapSubRecargoItem).collect(Collectors.toList());
    }

    private SubRecargoItem mapSubRecargoItem(SubRecargoRequestDto request) {
        if (request == null) return null;
        return SubRecargoItem.builder()
                .tipoSubRecargo(request.tipo())
                .subRecargoPorcentaje(request.subRecargoPorcentaje())
                .montoSubRecargo(request.montoSubRecargo())
                .build();
    }

    private List<ImpuestoAdicionalItem> mapImpuestoAdicionalItem(List<String> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(
                item -> ImpuestoAdicionalItem.builder().tipoImpuestoAdicional(item).build()
        ).collect(Collectors.toList());
    }

    private OtraMonedaDescuentoItem mapOtraMonedaDescuentoItem(OtraMonedaDetalleItemRequestDto otraMonedaDetalleItemRequestDto) {
        if (otraMonedaDetalleItemRequestDto == null) return null;
        return OtraMonedaDescuentoItem.builder()
                .precioOtraMoneda(otraMonedaDetalleItemRequestDto.precio())
                .descuentoOtraMoneda(otraMonedaDetalleItemRequestDto.descuento())
                .recargoOtraMoneda(otraMonedaDetalleItemRequestDto.recargo())
                .montoItemOtraMoneda(otraMonedaDetalleItemRequestDto.montoItem())
                .build();
    }

    /* Builder de descuentosORecargos */

    private List<DescuentoORecargo> mapDescuentoORecargo(List<DescuentoRecargoRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapDescuentoORecargo).collect(Collectors.toList());
    }

    private DescuentoORecargo mapDescuentoORecargo(DescuentoRecargoRequestDto descuentoORecargoRequestDto) {
        if (descuentoORecargoRequestDto == null) return null;
        return DescuentoORecargo.builder()
                .numeroLinea(descuentoORecargoRequestDto.numeroLinea())
                .tipoAjuste(descuentoORecargoRequestDto.tipoAjuste())
                .indicadorNorma1007(descuentoORecargoRequestDto.indicadorNorma1007())
                .descripcionDescuentoORecargo(descuentoORecargoRequestDto.descripcion())
                .tipoValor(descuentoORecargoRequestDto.tipoValor())
                .valorDescuentoORecargo(descuentoORecargoRequestDto.valorDescuentoORecargo())
                .montoDescuentoORecargo(descuentoORecargoRequestDto.montoDescuentoORecargo())
                .montoDescuentooRecargoOtraMoneda(descuentoORecargoRequestDto.montoDescuentooRecargoOtraMoneda())
                .indicadorFacturacionDescuentoORecargo(descuentoORecargoRequestDto.indicadorFacturacionDescuentoORecargo())
                .build();
    }

    /* Builder de paginas */

    private List<PaginaSubTotal> mapPaginas(List<PaginaSubTotalRequestDto> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return lista.stream().map(this::mapPagina).collect(Collectors.toList());
    }

    private PaginaSubTotal mapPagina(PaginaSubTotalRequestDto paginaSubtotalRequestDto) {
        if (paginaSubtotalRequestDto == null) return null;
        return PaginaSubTotal.builder()
                .paginaNo(paginaSubtotalRequestDto.nroPagina())
                .noLineaDesde(paginaSubtotalRequestDto.nroLineaDesde())
                .noLineaHasta(paginaSubtotalRequestDto.nroLineaHasta())
                .subtotalMontoGravadoPagina(paginaSubtotalRequestDto.subtotalMontoGravadoPagina())
                .subtotalMontoGravado1Pagina(paginaSubtotalRequestDto.subtotalMontoGravado1Pagina())
                .subtotalMontoGravado2Pagina(paginaSubtotalRequestDto.subtotalMontoGravado2Pagina())
                .subtotalMontoGravado3Pagina(paginaSubtotalRequestDto.subtotalMontoGravado3Pagina())
                .subtotalExentoPagina(paginaSubtotalRequestDto.subtotalExentoPagina())
                .subtotalItbisPagina(paginaSubtotalRequestDto.subtotalItbisPagina())
                .subtotalItbis1Pagina(paginaSubtotalRequestDto.subtotalItbis1Pagina())
                .subtotalItbis2Pagina(paginaSubtotalRequestDto.subtotalItbis2Pagina())
                .subtotalItbis3Pagina(paginaSubtotalRequestDto.subtotalItbis3Pagina())
                .subtotalImpuestoAdicionalPagina(paginaSubtotalRequestDto.subtotalImpuestoAdicionalPagina())
                .subtotalImpuestoAdicional(this.mapSubtotalImpuestoAdicional(paginaSubtotalRequestDto.subtotalImpuestoAdicional()))
                .montoSubtotalPagina(paginaSubtotalRequestDto.montoSubtotalPagina())
                .subtotalMontoNoFacturablePagina(paginaSubtotalRequestDto.subtotalMontoNoFacturablePagina())
                .build();
    }

    private PaginaSubTotal.SubtotalImpuestoAdicional mapSubtotalImpuestoAdicional(SubTotalImpuestoAdicionalRequestDto subtotalImpuestoAdicionalRequestDto) {
        if (subtotalImpuestoAdicionalRequestDto == null) return null;
        return PaginaSubTotal.SubtotalImpuestoAdicional.builder()
                .subtotalOtrosImpuesto(subtotalImpuestoAdicionalRequestDto.subtotalOtrosImpuesto())
                .subtotalImpuestoSelectivoConsumoEspecificoPagina(subtotalImpuestoAdicionalRequestDto.subtotalImpuestoSelectivoConsumoEspecificoPagina())
                .build();
    }

    /* Builder de informacion de referencia */

    private InformacionReferencia mapInformacionReferencia(InformacionReferenciaRequestDto informacionReferenciaRequestDto) {
        if (informacionReferenciaRequestDto == null) return null;
        return InformacionReferencia.builder()
                .NCFModificado(informacionReferenciaRequestDto.NCFModificado())
                .RNCOtroContribuyente(informacionReferenciaRequestDto.RNCOtroContribuyente())
                .fechaNCFModificado(informacionReferenciaRequestDto.fechaNCFModificado())
                .codigoModificacion(informacionReferenciaRequestDto.codigoModificacion())
                .razonModificado(informacionReferenciaRequestDto.razonModificado())
                .build();
    }

}
