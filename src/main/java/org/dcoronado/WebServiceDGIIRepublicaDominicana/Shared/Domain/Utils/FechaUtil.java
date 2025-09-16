package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class FechaUtil {

    private static final String REGEX_VALIDA_FORMATO_FECHA = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";

    public static boolean tieneFormatoFechaValido (final String fecha) {
        if (isNull(fecha)) return false;
        Pattern pattern = Pattern.compile(REGEX_VALIDA_FORMATO_FECHA);
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }

    public static LocalDate toDate(final String fechaString) {
        DateTimeFormatter parseador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fechaString, parseador);
    }

    public static String toTexto(Date fecha) {
        if (fecha == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(fecha);
    }

    public static LocalDateTime formatearString(final String fechaZonaHorarioString) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(fechaZonaHorarioString);
        return offsetDateTime.toLocalDateTime();
    }

    /**
     * Genera la fecha y hora actual como texto en formato dd-MM-yyyy HH:mm:ss para la zona horaria de Lima.
     *
     * @return Fecha y hora actual como cadena (formato corto).
     */
    public static String obtenerFechaHoraActualComoTextoCorto() {
        ZoneId zoneId = ZoneId.of("America/Lima");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return zonedDateTime.format(formatter);
    }

    /**
     * Genera la fecha y hora actual como texto en formato MySQL (yyyy-MM-dd HH:mm:ss) para la zona horaria de Lima.
     *
     * @return Fecha y hora actual como cadena en formato MySQL.
     */
    public static String obtenerFechaHoraActualComoTextoMySQL() {
        ZoneId zoneId = ZoneId.of("America/Lima");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formateador);
    }

    /**
     * Convierte una fecha en formato dd-MM-yyyy a formato MySQL yyyy-MM-dd.
     *
     * @param fechaOriginal Fecha como cadena en formato dd-MM-yyyy.
     * @return Fecha formateada como cadena en formato yyyy-MM-dd.
     */
    public static String convertirFechaAFormatoMySQL(final String fechaOriginal) {
        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaOriginal, formatoOriginal);
        DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fecha.format(formatoNuevo);
    }
}
