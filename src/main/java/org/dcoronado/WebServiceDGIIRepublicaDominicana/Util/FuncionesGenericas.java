package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;

public class FuncionesGenericas {

    private static final int ESCALA_DECIMALES = 2;

    private FuncionesGenericas() {
        // Evita instanciacion
    }

    public static String rellenarConCeros(final String texto, final int largo) {
        return String.format("%0".concat(String.valueOf(largo)).concat("d"), Integer.parseInt(texto));
    }

    public static String readFileFromResources(String fileName) {
        try (InputStream inputStream = FuncionesGenericas.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new NotFoundException("No se encontró el archivo: " + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InfrastructureException("Ocurrio un error al leer el file", e);
        }
    }

    /**
     *  Normaliza el monto a 2 decimales con HALF_UP.
     * No valida, solo formatea.
     */
    public static BigDecimal normalizarMonto(BigDecimal monto) {
        if (monto == null)
            throw new InvalidArgumentException("El monto no puede ser nulo");

        return monto.setScale(ESCALA_DECIMALES, RoundingMode.HALF_UP);
    }

    /**
     *  Valida que el monto cumpla las reglas:
     * - ≥ 0
     * - Máximo 16 enteros + 2 decimales
     */
    public static void validarMontoPositivo(BigDecimal monto) {
        if (monto == null)
            throw new InvalidArgumentException("El monto no puede ser nulo");

        if (monto.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidArgumentException("El monto debe ser positivo o cero");

    }

    public static boolean isLinux() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("nux") || os.contains("nix") || os.contains("aix");
    }

}
