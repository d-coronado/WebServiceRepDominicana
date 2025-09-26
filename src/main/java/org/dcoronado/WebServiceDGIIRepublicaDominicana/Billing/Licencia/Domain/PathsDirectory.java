package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain;


import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public final class PathsDirectory {

    public static final String ROOT = "fe_repdominicana_V2";
    public static final String CARPETA_CERTIFICADO = "certificado_digital";
    public static final String CARPETA_XML_CON_FIRMA = "xml_con_firma";

    /* Ambientes */
    public static final String AMBIENTE_PRUEBAS = "pruebas";
    public static final String AMBIENTE_CERTIFICACION = "certificacion";
    public static final String AMBIENTE_PRODUCCION = "produccion";

    /* Subcarpetas de cada ambiente */
    public static final String COMPROBANTES_EMISION = "comprobantes_emision";
    public static final String COMPROBANTES_RECEPCION = "comprobantes_recepcion";
    public static final String APROBACION_EMISION = "aprobacion_comercial_emision";
    public static final String APROBACION_RECEPCION = "aprobacion_comercial_recepcion";

    private PathsDirectory() {}


    public static String getRelativaCertificadoLicencia(String rnc, String nombreCerficado) {
        notBlank(rnc, "rnc required");
        notBlank(nombreCerficado, "nombreCerficado required");
        return PathsDirectory.ROOT + "/"+ rnc + "/" + PathsDirectory.CARPETA_CERTIFICADO + "/" + nombreCerficado;
    }

}
