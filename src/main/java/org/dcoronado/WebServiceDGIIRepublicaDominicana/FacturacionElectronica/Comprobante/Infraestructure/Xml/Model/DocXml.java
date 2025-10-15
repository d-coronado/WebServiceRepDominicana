package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DocXml {
    @NotNull(message = "TipoeCF {field.required}")
    @XmlElement(name = "TipoeCF")
    protected Integer tipoComprobante;

    @NotNull(message = "eNCF {field.required}")
    @XmlElement(name = "eNCF")
    protected String eNCF;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "FechaVencimientoSecuencia")
    protected String  fechaVencimientoSecuencia;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "IndicadorNotaCredito")
    protected Integer indicadorNotaCredito;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "IndicadorEnvioDiferido")
    protected Integer indicadorEnvioDiferido;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "IndicadorMontoGravado")
    protected Integer indicadorMontoGravado;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name="IndicadorServicioTodoIncluido")
    protected Integer indicadorServicioTodoIncluido;

    @XmlElement(name = "TipoIngresos")
    protected String tipoIngreso;

    @XmlElement(name = "TipoPago")
    protected Integer tipoPago;

    @XmlElement(name = "FechaLimitePago")
    protected String  fechaLimitePago;

    @XmlElement(name = "TerminoPago")
    protected String  terminoPago;

    @XmlElementWrapper(name = "TablaFormasPago")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "FormaDePago", type = FormaPagoXml.class,required = false)
    protected List<FormaPagoXml> tablaFormasPago;

    @XmlElement(name = "TipoCuentaPago")
    protected String tipoCuentaPago;

    @XmlElement(name = "NumeroCuentaPago")
    protected String numeroCuentaPago;

    @XmlElement(name = "BancoPago")
    protected String bancoPago;

    @XmlElement(name = "FechaDesde")
    protected String fechaDesde;

    @XmlElement(name = "FechaHasta")
    protected String fechaHasta;

    @XmlElement(name = "TotalPaginas")
    protected Integer totalPaginas;

}
