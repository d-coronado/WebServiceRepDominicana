package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "ECF")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComprobanteXmlExtendido {
    @XmlElement(name = "Encabezado")
    protected EncabezadoXml encabezado;

    @XmlElementWrapper(name = "DetallesItems")
    @XmlElement(name = "Item", type = LineaComprobanteXml.class)
    protected List<LineaComprobanteXml> detallesItems;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElementWrapper(name = "Subtotales")
    @XmlElement(name = "Subtotal", type = SubTotalXml.class)
    protected List<SubTotalXml> subTotales;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElementWrapper(name = "DescuentosORecargos")
    @XmlElement(name = "DescuentoORecargo", type = DescuentoORecargoXml.class)
    protected List<DescuentoORecargoXml> descuentosORecargos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElementWrapper(name = "Paginacion")
    @XmlElement(name = "Pagina")
    protected List<PaginaSubTotalXml> paginas;

    @XmlElement(name = "InformacionReferencia")
    protected InformacionReferenciaXml informacionReferencia;

    @XmlElement(name = "FechaHoraFirma")
    protected String fechaHoraFirma;

}
