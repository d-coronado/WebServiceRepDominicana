package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.resumen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.FormaPagoXml;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DocResumenXml {
    @NotNull(message = "TipoeCF {field.required}")
    @XmlElement(name = "TipoeCF")
    protected Integer tipoComprobante;

    @NotNull(message = "eNCF {field.required}")
    @XmlElement(name = "eNCF")
    protected String eNCF;

    @XmlElement(name = "TipoIngresos")
    protected String tipoIngreso;

    @XmlElement(name = "TipoPago")
    protected Integer tipoPago;

    @XmlElementWrapper(name = "TablaFormasPago")
    @XmlElement(name = "FormaDePago", type = FormaPagoXml.class)
    protected List<FormaPagoXml> tablaFormasPago;

}
