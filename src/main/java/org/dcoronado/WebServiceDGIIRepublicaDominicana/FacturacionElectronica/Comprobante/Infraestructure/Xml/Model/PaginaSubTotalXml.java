package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PaginaSubTotalXml {
    @XmlElement(name = "PaginaNo")
    private Integer paginaNo;

    @XmlElement(name = "NoLineaDesde")
    private Integer noLineaDesde;

    @XmlElement(name = "NoLineaHasta")
    private Integer noLineaHasta;

    @XmlElement(name = "SubtotalMontoGravadoPagina")
    private BigDecimal subtotalMontoGravadoPagina;

    @XmlElement(name = "SubtotalMontoGravado1Pagina")
    private BigDecimal subtotalMontoGravado1Pagina;

    @XmlElement(name = "SubtotalMontoGravado2Pagina")
    private BigDecimal subtotalMontoGravado2Pagina;

    @XmlElement(name = "SubtotalMontoGravado3Pagina")
    private BigDecimal subtotalMontoGravado3Pagina;

    @XmlElement(name="SubtotalExentoPagina")
    private BigDecimal subtotalExentoPagina;

    @XmlElement(name = "SubtotalItbisPagina")
    private BigDecimal subtotalItbisPagina;

    @XmlElement(name = "SubtotalItbis1Pagina")
    private BigDecimal subtotalItbis1Pagina;

    @XmlElement(name = "SubtotalItbis2Pagina")
    private BigDecimal subtotalItbis2Pagina;

    @XmlElement(name = "SubtotalItbis3Pagina")
    private BigDecimal subtotalItbis3Pagina;

    @XmlElement(name = "SubtotalImpuestoAdicionalPagina")
    private BigDecimal subtotalImpuestoAdicionalPagina;

    @XmlElement(name = "SubtotalImpuestoAdicional")
    private SubtotalImpuestoAdicionalXml subtotalImpuestoAdicional;

    @XmlElement(name = "MontoSubtotalPagina")
    private BigDecimal montoSubtotalPagina;

    @XmlElement(name = "SubtotalMontoNoFacturablePagina")
    private BigDecimal subtotalMontoNoFacturablePagina;
 }
