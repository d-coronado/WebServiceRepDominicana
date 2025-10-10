package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private final String numeroLinea;
    private List<CodigoItem> codigosItem;
    private Integer indicadorFacturacion;
    private RetencionItem retencionItem;
    private final String nombreItem;
    private Integer indicadorBienoServicio;
    private final String descripcionItem;
    private BigDecimal cantidadItem;
    private Integer unidadMedida;
    private Integer cantidadReferencia;
    private Integer unidadReferencia;
    private List<SubCantidadItem> tablaSubcantidad;
    private BigDecimal gradosAlcohol;
    private BigDecimal precioUnitarioReferencia;
    private String fechaElaboracion;
    private String fechaVencimiento;
    private BigDecimal precioUnitario;
    private BigDecimal descuentoMonto;
    private List<SubDescuentoItem> tablaSubDescuentoItem;
    private BigDecimal recargoMonto;
    private List<SubRecargoItem> tablaSubRecargoItem;
    private List<ImpuestoAdicionalItem> tablaImpuestoAdicionalItem;
    private OtraMonedaDescuentoItem otraMonedaDescuentoItem;
    private BigDecimal montoItem;
}
