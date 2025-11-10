package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContextoArchivoEnum {
    CERTIFICADO_DIGITAL("CERTIFICADO_DIGITAL"),
    COMPROBANTE("COMPROBANTES"),
    APROBACION_COMERCIAL("APROBACIONES_COMERCIALES"),
    OTRO("OTROS");

    private final String pathSegment;
}
