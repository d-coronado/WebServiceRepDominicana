package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum TipoComprobanteTributarioEnum {
    FACTURA_CREDITO_FISCAL(31, "Factura de Crédito Fiscal Electrónica", "factura_credito_fiscal"),
    FACTURA_CONSUMO(32, "Factura de Consumo Electrónica", "factura_consumo"),
    NOTA_DEBITO(33, "Nota de Débito Electrónica", "nota_debito"),
    NOTA_CREDITO(34, "Nota de Crédito Electrónica", "nota_credito"),
    COMPRAS(41, "Compras Electrónico", "compras"),
    GASTOS_MENORES(43, "Gastos Menores Electrónico", "gastos_menores"),
    REGIMENES_ESPECIALES(44, "Regímenes Especiales Electrónico", "regimenes_especiales"),
    GUBERNAMENTAL(45, "Gubernamental Electrónico", "gubernamental"),
    COMPROBANTE_EXPORTACION(46, "Comprobante de Exportaciones Electrónico", "comprobante_exportacion"),
    COMPROBANTE_PAGO_EXTERIOR(47, "Comprobante para Pagos al Exterior Electrónico", "comprobante_pago_exterior");


    private final Integer valor;
    private final String descripcion;
    private final String pathSegment;

//    /** Retorna el enum correspondiente al valor, o null si no existe */
//    public static TipoComprobanteTributarioEnum fromValorOrNull(String valor) {
//        return Arrays.stream(values())
//                .filter(tipo -> tipo.getValor().equals(valor))
//                .findFirst()
//                .orElse(null);
//    }

//    @JsonCreator
//    public static TipoComprobanteTributarioEnum fromValor(String valor) {
//        return Arrays.stream(values())
//                .filter(tipo -> tipo.valor.equals(valor))
//                .findFirst()
//                .orElseThrow(() -> new InvalidArgumentException("Valor inválido: " + valor));
//    }
}
