package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoOperacionArchivoLicenciaEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.TreeNode;

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
    public static TreeNode buildLicenciaTree(String rnc) {
        required(rnc, "rnc required");

        TreeNode root = new TreeNode(ROOT);
        root.agregarHijo(construirNodoLicencia(rnc));
        return root;
    }


    private static TreeNode construirNodoLicencia(String rnc) {
        TreeNode licencia = new TreeNode(rnc);
        licencia.agregarHijo(new TreeNode(ContextoArchivoEnum.CERTIFICADO_DIGITAL.getPathSegment()));
        licencia.agregarHijo(construirNodoComprobantes());
        licencia.agregarHijo(construirNodoAprobacionesComerciales());
        licencia.agregarHijo(new TreeNode(ContextoArchivoEnum.OTRO.getPathSegment()));
        return licencia;
    }

    private static TreeNode construirNodoComprobantes() {
        TreeNode comprobantes = new TreeNode(ContextoArchivoEnum.COMPROBANTE.getPathSegment());

        // emision, recepcion
        for (TipoOperacionArchivoLicenciaEnum tipo : TipoOperacionArchivoLicenciaEnum.values()) {
            comprobantes.agregarHijo(construirNodoTipoArchivo(tipo));
        }

        return comprobantes;
    }

    private static TreeNode construirNodoAprobacionesComerciales() {
        TreeNode aprobacionesComerciales = new TreeNode(ContextoArchivoEnum.APROBACION_COMERCIAL.getPathSegment());
        // emision, recepcion
        for (TipoOperacionArchivoLicenciaEnum tipo : TipoOperacionArchivoLicenciaEnum.values()) {
            aprobacionesComerciales.agregarHijo(construirNodoTipoArchivo(tipo));
        }

        return aprobacionesComerciales;
    }


    private static TreeNode construirNodoTipoArchivo(TipoOperacionArchivoLicenciaEnum tipoArchivo) {
        TreeNode nodoTipo = new TreeNode(tipoArchivo.getPathSegment());

        for (AmbienteEnum ambiente : AmbienteEnum.values()) {
            nodoTipo.agregarHijo(construirNodoAmbiente(ambiente));
        }

        return nodoTipo;
    }

    private static TreeNode construirNodoAmbiente(AmbienteEnum ambiente) {
        TreeNode nodoAmbiente = new TreeNode(ambiente.getPathSegment());

        for (TipoComprobanteTributarioEnum tipoComprobante : TipoComprobanteTributarioEnum.values()) {
            nodoAmbiente.agregarHijo(construirNodoComprobante(tipoComprobante));
        }
        return nodoAmbiente;
    }

    private static TreeNode construirNodoComprobante(TipoComprobanteTributarioEnum tipoComprobante) {
        return new TreeNode(tipoComprobante.getPathSegment());
    }

}