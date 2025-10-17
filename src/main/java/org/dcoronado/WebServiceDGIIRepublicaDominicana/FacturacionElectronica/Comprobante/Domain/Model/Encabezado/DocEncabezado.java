package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public final class DocEncabezado
 {
     private final String secuencia;
     private final String fechaVencimientoSecuencia;
     private final Integer indicadorNotaCredito;
     private final Integer indicadorEnvioDiferido;
     private final Integer indicadorMontoGravado;
     private final String tipoIngreso;
     private final Integer tipoPago;
     private final String fechaLimitePago;
     private final String terminoPago;
     List<FormaPago> tablaFormasPago;
     private final String tipoCuentaPago;
     private final String numeroCuentaPago;
     private final String bancoPago;
     private final String fechaDesde;
     private final String fechaHasta;
     private final Integer totalPaginas;
 }
