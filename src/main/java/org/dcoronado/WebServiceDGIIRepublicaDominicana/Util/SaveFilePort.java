package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

public interface SaveFilePort {

    /**
     * Guarda un archivo en la ruta especificada.
     *
     * @param path    Ruta relativa del archivo.
     * @param content Contenido del archivo.
     * @return Ruta absoluta donde se guardó el archivo.
     */
    String save(String path, byte[] content);
}
