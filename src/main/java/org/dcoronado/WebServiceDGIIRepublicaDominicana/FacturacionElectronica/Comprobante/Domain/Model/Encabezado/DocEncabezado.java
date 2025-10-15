package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.*;

@Builder
public record DocEncabezado(
        AmbienteEnum ambiente,
        TipoComprobanteTributarioEnum tipoComprobante,
        String secuencia,
        String fechaVencimientoSecuencia,
        Integer indicadorNotaCredito,
        Integer indicadorEnvioDiferido,
        Integer indicadorMontoGravado,
        String tipoIngreso,
        Integer tipoPago,
        String fechaLimitePago,
        String terminoPago,
        List<FormaPago> tablaFormasPago,
        String tipoCuentaPago,
        String numeroCuentaPago,
        String bancoPago,
        String fechaDesde,
        String fechaHasta,
        Integer totalPaginas
) {

    public DocEncabezado {
        notBlank(secuencia, "Secuencia obligatorio");

    }

}
