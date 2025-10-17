package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Transformer;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Response.ComprobanteResponse;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Dto.Transformer.DtoTransformer;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;


@Component
public class ComprobanteDtoTransformer extends DtoTransformer<ComprobanteResponse, Comprobante> {

    @Override
    public ComprobanteResponse fromObject(Comprobante comprobante) {
        if (isNull(comprobante)) throw new InvalidArgumentException("comprobante no puede ser null");

        return ComprobanteResponse.builder()
                .ambienteEnum(comprobante.getAmbienteEnum())
                .rncEmisor(comprobante.getEncabezado().getEmisorEncabezado().getRnc())
                .rncComprador(isNull(comprobante.getEncabezado().getCompradorEncabezado()) ? null : comprobante.getEncabezado().getCompradorEncabezado().getRnc())
                .encF(comprobante.getEncf())
                .fechaEmision(comprobante.getFechaEmision())
                .monto(comprobante.getEncabezado().getTotalesEncabezado().getMontoTotal())
                .fechaHoraFirma(comprobante.getFechaHoraFirma())
                .codigoSeguridad(comprobante.getCodigoSeguridad())
                .urlConsultaDgii(null)
                .build();
    }
}