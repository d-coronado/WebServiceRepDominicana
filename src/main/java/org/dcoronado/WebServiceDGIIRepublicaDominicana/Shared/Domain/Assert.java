package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.MissingParameterException;

import java.time.LocalDate;

public final class Assert {

    public static void notBlank(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new MissingParameterException(message);
        }
    }

    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new MissingParameterException(message);
        }
    }

    public static void matches(String value, String regex, String message) {
        if (value == null || !value.matches(regex)) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void maxLength(String value, int max, String message) {
        if (value != null && value.length() > max) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void minLength(String value, int min, String message) {
        if (value != null && value.length() < min) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void positive(Number number, String message) {
        if (number == null || number.doubleValue() <= 0) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void notPast(LocalDate date, String message) {
        if (date != null && date.isBefore(LocalDate.now())) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void notFuture(LocalDate date, String message) {
        if (date != null && date.isAfter(LocalDate.now())) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void dateBefore(LocalDate date, LocalDate max, String message) {
        if (date != null && date.isAfter(max)) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void dateAfter(LocalDate date, LocalDate min, String message) {
        if (date != null && date.isBefore(min)) {
            throw new InvalidArgumentException(message);
        }
    }

    public static <T> boolean esValorValido(T valor, T... permitidos) {
        if (valor == null) return false;
        for (T p : permitidos) {
            if (valor.equals(p)) return true;
        }
        return false;
    }

}
