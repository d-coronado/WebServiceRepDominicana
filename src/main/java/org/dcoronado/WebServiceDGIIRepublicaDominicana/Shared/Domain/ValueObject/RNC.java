package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Getter
@EqualsAndHashCode
public class RNC {

    private final String valor;

    private RNC(String valor, boolean skipValidation) {
        if (!skipValidation) {
            notBlank(valor, "RNC no puede estar vacío");
            validarFormato(valor);
        }
        this.valor = valor != null ? valor.trim() : null;
    }

    // Fábrica de negocio (validada)
    public static RNC of(String valor) {
        return new RNC(valor, false);
    }

    // Fábrica para reconstrucción desde BD (sin validación)
    public static RNC reconstruirDesdeBD(String valor) {
        return new RNC(valor, true);
    }

    private void validarFormato(String rnc) {
        String rncLimpio = rnc.replaceAll("[^0-9]", "");
        if (rncLimpio.length() != 9 && rncLimpio.length() != 11) {
            throw new InvalidArgumentException(
                    "RNC debe tener 9 o 11 dígitos, recibido: " + rnc
            );
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
