package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain;


import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoOperacionArchivoLicenciaEnum;

public final class RutasDirectoriosLicencia {

    public static final String ROOT = "FE_REPUBLICA_DOMINICANA_V2";

    private RutasDirectoriosLicencia() {
    }


    /**
     * Ruta base de la licencia (por RNC).
     */
    private static String baseRnc(String rnc) {
        return String.join("/", ROOT, rnc);
    }

    /**
     * Ruta base para un contexto específico (por ejemplo: comprobante, certificado_digital, otros...).
     */

    private static String baseContexto(String rnc, ContextoArchivoEnum contexto) {
        return String.join("/", baseRnc(rnc), contexto.getPathSegment());
    }

    /**
     * Ruta para certificados digitales.
     */
    public static String getRutaCertificadoDigital(String rnc) {
        return baseContexto(rnc, ContextoArchivoEnum.CERTIFICADO_DIGITAL);
    }

    /**
     * Ruta para la carpeta "otros".
     */
    public static String getRutaOtros(String rnc) {
        return baseContexto(rnc, ContextoArchivoEnum.OTRO);
    }

    /**
     * Ruta general para un comprobante o aprobación comercial.
     */
    public static String getRutaArchivoLicencia(
            String rnc,
            ContextoArchivoEnum contextoArchivoEnum,
            TipoOperacionArchivoLicenciaEnum tipoOperacion,
            AmbienteEnum ambiente,
            TipoComprobanteTributarioEnum tipoComprobante
    ) {
        return String.join("/",
                baseContexto(rnc, contextoArchivoEnum),
                tipoOperacion.getPathSegment().toLowerCase(),
                ambiente.getPathSegment().toLowerCase(),
                tipoComprobante.getPathSegment()
        );
    }

}
