package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class Item {

    private final String numeroLinea;
    private final List<CodigoItem> codigosItem;
    private final Integer indicadorFacturacion;
    private final RetencionItem retencionItem;
    private final String nombreItem;
    private final Integer indicadorBienoServicio;
    private final String descripcionItem;
    private BigDecimal cantidadItem;
    private final Integer unidadMedida;
    private final Integer cantidadReferencia;
    private final Integer unidadReferencia;
    private final List<SubCantidadItem> tablaSubcantidad;
    private BigDecimal gradosAlcohol;
    private BigDecimal precioUnitarioReferencia;
    private final String fechaElaboracion;
    private final String fechaVencimiento;
    private BigDecimal precioUnitario;
    private BigDecimal descuentoMonto;
    private final List<SubDescuentoItem> tablaSubDescuentoItem;
    private BigDecimal recargoMonto;
    private final List<SubRecargoItem> tablaSubRecargoItem;
    private final List<ImpuestoAdicionalItem> tablaImpuestoAdicionalItem;
    private final OtraMonedaDescuentoItem otraMonedaDescuentoItem;
    private BigDecimal montoItem;
}
