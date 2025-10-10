package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoComprobanteTributarioEnum {
    FACTURA_CREDITO_FISCAL("31","Factura de Crédito Fiscal Electrónica"),
    FACTURA_CONSUMO("32","Factura de Consumo Electrónica"),
    NOTA_DEBITO("33","Nota de Débito Electrónica"),
    NOTA_CREDITO("34","Nota de Crédito Electrónica"),
    COMPRAS("41", "Compras Electrónico"),
    GASTOS_MENORES("43", "Gastos Menores Electrónico"),
    REGIMENES_ESPECIALES("44", "Regímenes Especiales Electrónico"),
    GUBERNAMENTAL("45", "Gubernamental Electrónico"),
    COMPROBANTE_EXPORTACION("46", "Comprobante de Exportaciones Electrónico"),
    COMPROBANTE_PAGO_EXTERIOR("47", "Comprobante para Pagos al Exterior Electrónico");

    private final String valor;
    private final String descripcion;

    /** Retorna el enum correspondiente al valor, o null si no existe */
    public static TipoComprobanteTributarioEnum fromValorOrNull(String valor) {
        return Arrays.stream(values())
                .filter(tipo -> tipo.getValor().equals(valor))
                .findFirst()
                .orElse(null);
    }
}
