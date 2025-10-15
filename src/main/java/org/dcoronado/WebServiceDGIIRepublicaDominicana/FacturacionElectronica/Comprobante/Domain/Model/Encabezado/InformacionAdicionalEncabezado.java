package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;

@Builder
public record InformacionAdicionalEncabezado(
        String fechaEmbarque,
        String numeroEmbarque,
        String numeroContenedor,
        String numeroReferencia,
        Double pesoBruto,
        Double pesoNeto,
        Integer unidadPesoBruto,
        Integer unidadPesoNeto,
        Double cantidadBulto,
        Integer unidadBulto,
        Double volumenBulto,
        Integer unidadVolumen

) {
}
