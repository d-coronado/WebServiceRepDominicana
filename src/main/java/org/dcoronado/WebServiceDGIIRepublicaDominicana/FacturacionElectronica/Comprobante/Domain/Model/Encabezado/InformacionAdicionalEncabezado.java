package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class InformacionAdicionalEncabezado {
    private final String fechaEmbarque;
    private final String numeroEmbarque;
    private final String numeroContenedor;
    private final String numeroReferencia;
    private final Double pesoBruto;
    private final Double pesoNeto;
    private final Integer unidadPesoBruto;
    private final Integer unidadPesoNeto;
    private final Double cantidadBulto;
    private final Integer unidadBulto;
    private final Double volumenBulto;
    private final Integer unidadVolumen;
}
