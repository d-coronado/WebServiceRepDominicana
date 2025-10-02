package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.OS;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public class FuncionesGenericas {

    private FuncionesGenericas(){
        // Evita instanciacion
    }

    public static String rellenarConCeros(final String texto,final int largo) {
        return String.format("%0".concat(String.valueOf(largo)).concat("d"), Integer.parseInt(texto));
    }

    public static OS getCurrentOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return OS.WINDOWS;
        if (os.contains("mac")) return OS.MAC;
        if (os.contains("nix") || os.contains("nux")) return OS.LINUX;
        return OS.OTHER;
    }

    public static String readFileFromResources(String fileName) {
        try (InputStream inputStream = FuncionesGenericas.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new NotFoundException("No se encontró el archivo: " + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InfrastructureException("Ocurrio un error al leer el file",e);
        }
    }

    public static void validateArchivo(String nombreDocumento, byte[] archivo) {
        notBlank(nombreDocumento,"Nombre Documento required");
        if (!nombreDocumento.matches("[a-zA-Z0-9._-]+"))
            throw new InvalidArgumentException("Nombre de archivo contiene caracteres inválidos");
        if (archivo.length == 0) throw new InvalidArgumentException ("Archivo no puede estar vacío");
    }

}
