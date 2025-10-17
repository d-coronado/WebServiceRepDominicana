package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.TiposComprobantes;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorEspecificoTemplate;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils.FechaUtil;

import java.util.List;
import java.util.Optional;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.ComprobanteConstantes.*;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.*;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.validarMontoPositivo;

public class FacturaCreditoFiscalValidator extends ComprobanteValidatorEspecificoTemplate {



    @Override
    protected void validarDocEncabezadoEspecifico(DocEncabezado doc, String fechaEmision) {
        // validar fecha de vencimiento de secuencia
        notBlank(doc.getFechaVencimientoSecuencia(), "fechaVencimientoSecuencia required");
        if (!FechaUtil.tieneFormatoFechaValido(doc.getFechaVencimientoSecuencia()))
            throw new InvalidArgumentException("fechaVencimientoSecuencia tiene formato inválido.Debe tener formato dd-MM-AAAA");

        // validar indicadorMontoGravado
        required(doc.getIndicadorMontoGravado(), "indicadorMontoGravado required");
        if (!esValorValido(doc.getIndicadorMontoGravado(), ITEM_ITBIS_NO_INCLUIDO, ITEM_ITBIS_INCLUIDO))
            throw new InvalidArgumentException("indicadorMontoGravado inválido. Solo puede ser 0 o 1");

        // validar TipoIngreso
        notBlank(doc.getTipoIngreso(), "tipoIngreso required");
        if (!esValorValido(doc.getTipoIngreso(), INGRESO_POR_OPERACIONES, INGRESO_FINANCIEROS, INGRESO_EXTRAORDINARIO, INGRESO_POR_ARRENDAMIENTO, INGRESO_POR_VENTA_ACTIVO_DEPRECIABLE, INGRESO_OTROS))
            throw new InvalidArgumentException("tipoIngreso inválido. Solo puede ser uno de: 01, 02, 03, 04, 05, 06");

        // validar TipoPago
        required(doc.getTipoPago(), "tipoPago required");
        if (!esValorValido(doc.getTipoPago(), PAGO_CONTADO, PAGO_CREDITO) || doc.getTipoPago().equals(PAGO_GRATUITO))
            throw new InvalidArgumentException("Tipo pago inválido para Factura Crédito Fiscal. Solo puede ser 1 o 2");


        // validar fecha limite de pago para tipo de pago credito
        if (doc.getTipoPago().equals(PAGO_CREDITO)) {
            required(doc.getFechaLimitePago(), "fechaLimitePago required");
            if (!FechaUtil.tieneFormatoFechaValido(doc.getFechaLimitePago()))
                throw new InvalidArgumentException("fechaLimitePago inválida. Debe tener formato dd-MM-AAAA");

            if (!FechaUtil.isAfter(doc.getFechaLimitePago(), fechaEmision))
                throw new IllegalArgumentException("La 'fechaLimitePago' debe ser posterior a la 'fechaEmision'");
        }

        // Validar formas de pago si es que envían, debido a que es opcional.
        Optional.ofNullable(doc.getTablaFormasPago())
                .filter(list -> !list.isEmpty())
                .ifPresent(list -> list
                        .forEach(this::validarFormaPago));


        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.getTipoCuentaPago() != null) {
            if (!esValorValido(doc.getTipoCuentaPago(), TIPO_CUENTA_PAGO_CTACORRIENTE, TIPO_CUENTA_PAGO_AHORRO, TIPO_CUENTA_PAGO_OTRA))
                throw new InvalidArgumentException("Tipo cuenta de pago invalido");
        }

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.getFechaDesde() != null) {
            if (!FechaUtil.tieneFormatoFechaValido(doc.getFechaDesde()))
                throw new InvalidArgumentException("fechaDesde inválida. Debe tener formato dd-MM-AAAA");
        }

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.getFechaHasta() != null) {
            if (!FechaUtil.tieneFormatoFechaValido(doc.getFechaHasta()))
                throw new InvalidArgumentException("fechaHasta inválida. Debe tener formato dd-MM-AAAA");
        }

        // validar tipo cuenta de pago is que envian , debido a que es opcional
        if (doc.getFechaDesde() != null && doc.getFechaHasta() != null) {
            if (FechaUtil.isBefore(doc.getFechaHasta(), doc.getFechaDesde())) {
                throw new InvalidArgumentException("fechaHasta debe ser mayor o igual a fechaDesde");
            }
        }

        // FALTA VALIDAR EL NRO DE PAGINAS
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

    private void validarFormaPago(FormaPago fp) {
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
    }


}
