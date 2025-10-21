package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoOperacionArchivoLicenciaEnum {
    EMISION("EMISION"),
    RECEPCION("RECEPCION");

    private final String pathSegment;
}
