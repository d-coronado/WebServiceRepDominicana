package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import java.util.Arrays;

@AllArgsConstructor
public enum Ambiente {
    PRUEBAS("1"),
    CERTIFICACION("2"),
    PRODUCCION("3");

    private final String codigo;

    // Cuando se deserializa (JSON → Java)
    @JsonCreator
    public static Ambiente fromCodigo(String codigo) {
        return Arrays.stream(values())
                .filter(a -> a.codigo.equals(codigo))
                .findFirst()
                .orElseThrow(() -> new InvalidArgumentException("Código de ambiente inválido: " + codigo));
    }

    // Cuando se serializa (Java → JSON)
    @JsonValue
    public String getCodigo() {
        return codigo;
    }
}
