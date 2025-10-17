package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain;

import java.math.BigDecimal;

public final class ComprobanteConstantes {

    private ComprobanteConstantes() {
    } // evita instanciación

    public static final String VERSION_FORMATO = "1.0";
    public static final BigDecimal UMBRAL_FACTURA_CONSUMO_RESUMIDA = new BigDecimal("250000.00");

    // indicadorMontoGravado
    public static final int ITEM_ITBIS_NO_INCLUIDO = 0;
    public static final int ITEM_ITBIS_INCLUIDO = 1;

    // tipoIngreso
    public static final String INGRESO_POR_OPERACIONES = "01";
    public static final String INGRESO_FINANCIEROS = "02";
    public static final String INGRESO_EXTRAORDINARIO = "03";
    public static final String INGRESO_POR_ARRENDAMIENTO = "04";
    public static final String INGRESO_POR_VENTA_ACTIVO_DEPRECIABLE = "05";
    public static final String INGRESO_OTROS = "06";

    // tipoPago
    public static final int PAGO_CONTADO = 1;
    public static final int PAGO_CREDITO = 2;
    public static final int PAGO_GRATUITO = 3;

    // Formas de pago
    public static final int FORMA_PAGO_EFECTIVO = 1;
    public static final int FORMA_PAGO_CHEQUE_TRANFERENCIA_DEPOSITO = 2;
    public static final int FORMA_PAGO_TARJETA_DEBITO_CREDITO = 3;
    public static final int FORMA_PAGO_VENTA_CREDITO = 4;
    public static final int FORMA_PAGO_BONO_CERTIFCADOREGALO = 5;
    public static final int FORMA_PAGO_PERMUTA = 6;
    public static final int FORMA_PAGO_NOTA_DE_CREDITO = 7;
    public static final int FORMA_PAGO_OTROS = 8;

    // Tipos de cuenta de pago
    public static final String TIPO_CUENTA_PAGO_CTACORRIENTE = "CT";
    public static final String TIPO_CUENTA_PAGO_AHORRO = "AH";
    public static final String TIPO_CUENTA_PAGO_OTRA = "OT";
}
