package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class LineaComprobanteXml {
    @XmlElement(name = "NumeroLinea")
    private String numeroLinea;

    @XmlElementWrapper(name = "TablaCodigosItem")
    @XmlElement(name = "CodigosItem", type = CodigoItemXml.class)
    private List<CodigoItemXml> codigosItem;

    @XmlElement(name = "IndicadorFacturacion")
    private Integer indicadorFacturacion;

    @XmlElement(name = "Retencion")
    private RetencionXml retencion;

    @XmlElement(name = "NombreItem")
    private String nombreItem;

    @XmlElement(name = "IndicadorBienoServicio")
    private Integer indicadorBienoServicio;

    @XmlElement(name = "DescripcionItem")
    private String descripcionItem;

    @XmlElement(name = "CantidadItem")
    private BigDecimal cantidadItem;

    @XmlElement(name = "UnidadMedida")
    private Integer unidadMedida;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "CantidadReferencia")
    private Integer cantidadReferencia;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "UnidadReferencia")
    private Integer unidadReferencia;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElementWrapper(name = "TablaSubcantidad")
    @XmlElement(name = "SubcantidadItem", type = SubCantidadItemXml.class)
    private List<SubCantidadItemXml> tablaSubcantidad;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "GradosAlcohol")
    private BigDecimal gradosAlcohol;

    @XmlElement(name = "PrecioUnitarioReferencia")
    private BigDecimal precioUnitarioReferencia;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "FechaElaboracion")
    private String fechaElaboracion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name="FechaVencimientoItem")
    private String fechaVencimiento;

    @XmlElement(name = "PrecioUnitarioItem")
    private BigDecimal precioUnitario;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "DescuentoMonto")
    private BigDecimal descuentoMonto;


    @XmlElementWrapper(name = "TablaSubDescuento")
    @XmlElement(name = "SubDescuento", type= SubDescuentoXml.class)
    private List<SubDescuentoXml> tablaSubDescuento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "RecargoMonto")
    private BigDecimal recargoMonto;

    @XmlElementWrapper(name = "TablaSubRecargo")
    @XmlElement(name = "SubRecargo",type = SubRecargoXml.class )
    private List<SubRecargoXml> tablaSubRecargo;

    @XmlElementWrapper(name = "TablaImpuestoAdicional")
    @XmlElement(name = "ImpuestoAdicional", type= ImpuestoAdicionalDetalleXml.class)
    private List<ImpuestoAdicionalDetalleXml> tablaImpuestoAdicional;

    @XmlElement(name = "OtraMonedaDetalle")
    private OtraMonedaDescuentoXml otraMonedaDetalle;

    @XmlElement(name = "MontoItem")
    private BigDecimal montoItem;
}
