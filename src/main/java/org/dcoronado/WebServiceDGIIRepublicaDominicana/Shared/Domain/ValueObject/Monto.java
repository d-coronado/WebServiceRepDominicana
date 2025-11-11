package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject;

import lombok.Getter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
public class Monto {

    private static final int ESCALA_DECIMALES = 2;
    private final BigDecimal valor;

    private Monto(BigDecimal valor) {
        if (valor == null)
            throw new InvalidArgumentException("El monto no puede ser nulo");

        if (valor.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidArgumentException("El monto debe ser positivo o cero");

        this.valor = valor.setScale(ESCALA_DECIMALES, RoundingMode.HALF_UP);
    }

    public static Monto of(BigDecimal valor) {
        return new Monto(valor);
    }

    public boolean esCero() {
        return valor.compareTo(BigDecimal.ZERO) == 0;
    }

    public Monto sumar(Monto otro) {
        return new Monto(this.valor.add(otro.valor));
    }

    public Monto restar(Monto otro) {
        return new Monto(this.valor.subtract(otro.valor));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monto monto)) return false;
        return valor.compareTo(monto.valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor.toPlainString();
    }
}
