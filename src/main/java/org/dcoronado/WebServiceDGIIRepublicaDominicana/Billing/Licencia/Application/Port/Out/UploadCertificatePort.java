package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

public interface UploadCertificatePort {

    /**
     * Guarda un archivo en la ruta especificada.
     *
     * @param path    Ruta relativa del archivo.
     * @param content Contenido del archivo.
     * @return Ruta absoluta donde se guardó el archivo.
     */
    String save(String path, byte[] content);

    /**
     * Lee un archivo desde la ruta especificada.
     *
     * @param path Ruta completa del archivo.
     * @return Contenido del archivo.
     */
    byte[] load(String path);

    /**
     * Elimina un archivo de la ruta especificada.
     *
     * @param path Ruta completa del archivo.
     */
    void delete(String path);
}
