package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AmbienteEnum {
    PRUEBAS("PRUEBAS","testecf"),
    CERTIFICACION("CERTIFICACION","testecf"), // despues cambiar por "certecf"
    PRODUCCION("PRODUCCION","testecf"); // despues cambiar por "ecf"

    private final String pathSegment;
    private final String urlPathDgii;

}
