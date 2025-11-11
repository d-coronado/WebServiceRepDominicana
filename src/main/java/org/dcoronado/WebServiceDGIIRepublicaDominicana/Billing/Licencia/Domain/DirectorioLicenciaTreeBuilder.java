package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoOperacionArchivoLicenciaEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.TreeNodeDto;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.RutasDirectoriosLicencia.*;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

/**
 * Constructor de árbol de directorios para licencias DGII.
 * Genera la estructura de carpetas basada en RNC, ambientes y tipos de comprobantes.
 */
public final class DirectorioLicenciaTreeBuilder {


    private DirectorioLicenciaTreeBuilder() {
    }

    /**
     * Construye el árbol completo de directorios para una licencia.
     * Estructura generada:
     * - ROOT/
     * ├── rncLicencia/
     * │   ├── certificado_digital/
     * │   ├── comprobantes/
     * │   │   ├── recepcion/
     * │   │   └── emision/
     * │   │       ├── pruebas/
     * │   │       ├── certificacion/
     * │   │       └── produccion/
     * │   │           ├── factura_credito_fiscal/
     * │   │           ├── factura_consumo/
     * │   │           ├── nota_credito/
     * │   │           ├── nota_debito/
     * │   └── otros/
     * │
     */
    public static TreeNodeDto buildLicenciaTree(String rnc) {
        required(rnc, "rnc required");

        TreeNodeDto root = new TreeNodeDto(ROOT);
        root.agregarHijo(construirNodoLicencia(rnc));
        return root;
    }


    private static TreeNodeDto construirNodoLicencia(String rnc) {
        TreeNodeDto licencia = new TreeNodeDto(rnc);
        licencia.agregarHijo(new TreeNodeDto(ContextoArchivoEnum.CERTIFICADO_DIGITAL.getPathSegment()));
        licencia.agregarHijo(construirNodoComprobantes());
        licencia.agregarHijo(construirNodoAprobacionesComerciales());
        licencia.agregarHijo(new TreeNodeDto(ContextoArchivoEnum.OTRO.getPathSegment()));
        return licencia;
    }

    private static TreeNodeDto construirNodoComprobantes() {
        TreeNodeDto comprobantes = new TreeNodeDto(ContextoArchivoEnum.COMPROBANTE.getPathSegment());

        // emision, recepcion
        for (TipoOperacionArchivoLicenciaEnum tipo : TipoOperacionArchivoLicenciaEnum.values()) {
            comprobantes.agregarHijo(construirNodoTipoArchivo(tipo));
        }

        return comprobantes;
    }

    private static TreeNodeDto construirNodoAprobacionesComerciales() {
        TreeNodeDto aprobacionesComerciales = new TreeNodeDto(ContextoArchivoEnum.APROBACION_COMERCIAL.getPathSegment());
        // emision, recepcion
        for (TipoOperacionArchivoLicenciaEnum tipo : TipoOperacionArchivoLicenciaEnum.values()) {
            aprobacionesComerciales.agregarHijo(construirNodoTipoArchivo(tipo));
        }

        return aprobacionesComerciales;
    }


    private static TreeNodeDto construirNodoTipoArchivo(TipoOperacionArchivoLicenciaEnum tipoArchivo) {
        TreeNodeDto nodoTipo = new TreeNodeDto(tipoArchivo.getPathSegment());

        for (AmbienteEnum ambiente : AmbienteEnum.values()) {
            nodoTipo.agregarHijo(construirNodoAmbiente(ambiente));
        }

        return nodoTipo;
    }

    private static TreeNodeDto construirNodoAmbiente(AmbienteEnum ambiente) {
        TreeNodeDto nodoAmbiente = new TreeNodeDto(ambiente.getPathSegment());

        for (TipoComprobanteTributarioEnum tipoComprobante : TipoComprobanteTributarioEnum.values()) {
            nodoAmbiente.agregarHijo(construirNodoComprobante(tipoComprobante));
        }
        return nodoAmbiente;
    }

    private static TreeNodeDto construirNodoComprobante(TipoComprobanteTributarioEnum tipoComprobante) {
        return new TreeNodeDto(tipoComprobante.getPathSegment());
    }

}