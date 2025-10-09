package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.OSEnum;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public class FuncionesGenericas {

    private static final int ESCALA_DECIMALES = 2;
    private static final int MAX_ENTEROS = 16;

    private FuncionesGenericas() {
        // Evita instanciacion
    }

    public static String rellenarConCeros(final String texto, final int largo) {
        return String.format("%0".concat(String.valueOf(largo)).concat("d"), Integer.parseInt(texto));
    }

    public static OSEnum getCurrentOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return OSEnum.WINDOWS;
        if (os.contains("mac")) return OSEnum.MAC;
        if (os.contains("nix") || os.contains("nux")) return OSEnum.LINUX;
        return OSEnum.OTHER;
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

    public static void validateArchivo(String nombreDocumento, byte[] archivo) {
        notBlank(nombreDocumento, "Nombre Documento required");
        if (!nombreDocumento.matches("[a-zA-Z0-9._-]+"))
            throw new InvalidArgumentException("Nombre de archivo contiene caracteres inválidos");
        if (archivo.length == 0) throw new InvalidArgumentException("Archivo no puede estar vacío");
    }

    /**
     * Normaliza el monto a 2 decimales y valida:
     * - ≥ 0
     * - Máx 16 enteros + 2 decimales
     */
    public static BigDecimal validarMonto(BigDecimal monto) {
        // Normalizar siempre a 2 decimales con HALF_UP
        BigDecimal normalizado = monto.setScale(ESCALA_DECIMALES, RoundingMode.HALF_UP);

        if (normalizado.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidArgumentException("El monto debe ser positivo o cero");

        int enteros = normalizado.precision() - normalizado.scale();
        if (enteros > MAX_ENTEROS) {
            throw new InvalidArgumentException(
                    "El monto excede el límite de " + MAX_ENTEROS + " enteros"
            );
        }

        return normalizado;
    }


}
