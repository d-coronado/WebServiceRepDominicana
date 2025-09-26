package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.PathsDirectory.*;

public final class DirectorioTreeBuilder {

    private DirectorioTreeBuilder() {}

    /* Crea la estructura de directorios basada en el RNC, bajo la carpeta raíz ROOT */
    public static DirectorioNode buildLicenciaTree(String rnc) {
        DirectorioNode root = new DirectorioNode(ROOT);

        DirectorioNode licenciaRnc = new DirectorioNode(rnc);
        licenciaRnc.agregarHijo(new DirectorioNode(CARPETA_CERTIFICADO));

        DirectorioNode xmlConFirma = new DirectorioNode(CARPETA_XML_CON_FIRMA);
        xmlConFirma.agregarHijo(crearAmbiente(AMBIENTE_PRUEBAS));
        xmlConFirma.agregarHijo(crearAmbiente(AMBIENTE_CERTIFICACION));
        xmlConFirma.agregarHijo(crearAmbiente(AMBIENTE_PRODUCCION));

        licenciaRnc.agregarHijo(xmlConFirma);
        root.agregarHijo(licenciaRnc);

        return root;
    }

    private static DirectorioNode crearAmbiente(String nombreAmbiente) {
        DirectorioNode ambiente = new DirectorioNode(nombreAmbiente);
        ambiente.agregarHijo(new DirectorioNode(COMPROBANTES_EMISION));
        ambiente.agregarHijo(new DirectorioNode(COMPROBANTES_RECEPCION));
        ambiente.agregarHijo(new DirectorioNode(APROBACION_EMISION));
        ambiente.agregarHijo(new DirectorioNode(APROBACION_RECEPCION));
        return ambiente;
    }
}
