package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils.FechaUtil;

import java.util.List;
import java.util.Optional;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.ComprobanteConstantes.*;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.*;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.validarMontoPositivo;

public class FacturaCreditoFiscalValidator implements ComprobanteValidator {
    @Override
    public void execute(Comprobante comprobante) {
        validarEncabezado(comprobante.encabezado());
        validarItems(comprobante.items());
        validarSubtotales(comprobante.subtotales());
        validarDescuentosORecargos(comprobante.descuentosORecargos());
        validarPaginas(comprobante.paginas());
        validarInformacionReferencia(comprobante.informacionReferencia());
        validarFechaHoraFirma(comprobante.fechaHoraFirma());
    }

    // ----------------- VALIDACIÓN DEL ENCABEZADO -----------------
    private void validarEncabezado(Encabezado encabezado) {
        validarDoc(encabezado.docEncabezado(), encabezado.emisorEncabezado().fechaEmision());
        validarComprador(encabezado.compradorEncabezado());
        validarInformacionAdicional(encabezado.informacionAdicionalEncabezado());
        validarTransporte(encabezado.transporteEncabezado());
        validarTotales(encabezado.totalesEncabezado());
        validarOtraMoneda(encabezado.otraMonedaEncabezado());
    }

    private void validarDoc(DocEncabezado doc, String fechaEmision) {

        // validar fecha de vencimiento de secuencia
        notBlank(doc.fechaVencimientoSecuencia(), "fechaVencimientoSecuencia required");
        if (!FechaUtil.tieneFormatoFechaValido(doc.fechaVencimientoSecuencia()))
            throw new InvalidArgumentException("fechaVencimientoSecuencia tiene formato inválido.Debe tener formato dd-MM-AAAA");

        // validar indicadorMontoGravado
        required(doc.indicadorMontoGravado(), "indicadorMontoGravado required");
        if (!esValorValido(doc.indicadorMontoGravado(), ITEM_ITBIS_NO_INCLUIDO, ITEM_ITBIS_INCLUIDO))
            throw new InvalidArgumentException("indicadorMontoGravado inválido. Solo puede ser 0 o 1");

        // validar TipoIngreso
        notBlank(doc.tipoIngreso(), "tipoIngreso required");
        if (!esValorValido(doc.tipoIngreso(), INGRESO_POR_OPERACIONES, INGRESO_FINANCIEROS, INGRESO_EXTRAORDINARIO, INGRESO_POR_ARRENDAMIENTO, INGRESO_POR_VENTA_ACTIVO_DEPRECIABLE, INGRESO_OTROS))
            throw new InvalidArgumentException("tipoIngreso inválido. Solo puede ser uno de: 01, 02, 03, 04, 05, 06");

        // validar TipoPago
        required(doc.tipoPago(), "tipoPago required");
        if (!esValorValido(doc.tipoPago(), PAGO_CONTADO, PAGO_CREDITO) || doc.tipoPago().equals(PAGO_GRATUITO))
            throw new InvalidArgumentException("Tipo pago inválido para Factura Crédito Fiscal. Solo puede ser 1 o 2");


        // validar fecha limite de pago para tipo de pago credito
        if (doc.tipoPago().equals(PAGO_CREDITO)) {
            required(doc.fechaLimitePago(), "fechaLimitePago required");
            if (!FechaUtil.tieneFormatoFechaValido(doc.fechaLimitePago()))
                throw new InvalidArgumentException("fechaLimitePago inválida. Debe tener formato dd-MM-AAAA");

            if (!FechaUtil.isAfter(doc.fechaLimitePago(), fechaEmision))
                throw new IllegalArgumentException("La 'fechaLimitePago' debe ser posterior a la 'fechaEmision'");
        }

        // Validar formas de pago si es que envian , debido a que es opcional.
        Optional.ofNullable(doc.tablaFormasPago())
                .filter(list -> !list.isEmpty())
                .ifPresent(list -> list.forEach(fp -> {
                    required(fp, "FormaPago required");
                    required(fp.getTipo(), "Tipo de FormaPago required");
                    if (!esValorValido(fp.getTipo(),
                            FORMA_PAGO_EFECTIVO,
                            FORMA_PAGO_CHEQUE_TRANFERENCIA_DEPOSITO,
                            FORMA_PAGO_TARJETA_DEBITO_CREDITO,
                            FORMA_PAGO_VENTA_CREDITO,
                            FORMA_PAGO_BONO_CERTIFCADOREGALO,
                            FORMA_PAGO_PERMUTA,
                            FORMA_PAGO_NOTA_DE_CREDITO,
                            FORMA_PAGO_OTROS)) {
                        throw new InvalidArgumentException("Forma de pago invalid");
                    }

                    required(fp.getMonto(), "monto required");
                    validarMontoPositivo(fp.getMonto());
                }));

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.tipoCuentaPago() != null) {
            if (!esValorValido(doc.tipoCuentaPago(), TIPO_CUENTA_PAGO_CTACORRIENTE, TIPO_CUENTA_PAGO_AHORRO, TIPO_CUENTA_PAGO_OTRA))
                throw new InvalidArgumentException("Tipo cuenta de pago invalido");
        }

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.fechaDesde() != null) {
            if (!FechaUtil.tieneFormatoFechaValido(doc.fechaDesde()))
                throw new InvalidArgumentException("fechaDesde inválida. Debe tener formato dd-MM-AAAA");
        }

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.fechaHasta() != null) {
            if (!FechaUtil.tieneFormatoFechaValido(doc.fechaHasta()))
                throw new InvalidArgumentException("fechaHasta inválida. Debe tener formato dd-MM-AAAA");
        }

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (FechaUtil.isBefore(doc.fechaHasta(), doc.fechaDesde()))
            throw new InvalidArgumentException("fechaHasta debe ser mayor o igual a fechaDesde");

        // FALTA VALIDAR EL NRO DE PAGINAS

    }

    private void validarComprador(CompradorEncabezado comprador) {
        // validar campos internos de comprador
    }

    private void validarInformacionAdicional(InformacionAdicionalEncabezado info) {
        // validar contenido
    }

    private void validarTransporte(TransporteEncabezado transporte) {
        // validar campos internos
    }

    private void validarTotales(TotalesEncabezado totales) {
        // validar montos y consistencia
    }

    private void validarOtraMoneda(OtraMonedaEncabezado otraMoneda) {
        // validar campos internos si aplica
    }

    // ----------------- VALIDACIÓN DE OTROS ATRIBUTOS DEL COMPROBANTE -----------------
    private void validarItems(List<Item> items) {

    }

    private void validarSubtotales(List<SubTotal> subtotales) {
        // validar existencia y valores correctos
    }

    private void validarDescuentosORecargos(List<DescuentoORecargo> descuentosORecargos) {
        // validar existencia y consistencia
    }

    private void validarPaginas(List<PaginaSubTotal> paginas) {
        // validar consistencia de páginas
    }

    private void validarInformacionReferencia(InformacionReferencia infoRef) {
        // validar si aplica para este tipo de comprobante
    }

    private void validarFechaHoraFirma(String fechaHoraFirma) {
    }
}
