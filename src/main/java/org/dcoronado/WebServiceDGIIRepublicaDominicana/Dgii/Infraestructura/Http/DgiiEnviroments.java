package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

@Component
public class DgiiEnviroments {

    private DgiiEnviroments() {
        // Evita instanciación
    }

    // Bases por ambiente
    private final String BASE_URL_PRUEBAS = "https://ecf.dgii.gov.do/testecf";
    private final String BASE_URL_CERTIFICACION = "https://ecf.dgii.gov.do/testecf";
    private final String BASE_URL_PRODUCCION = "https://ecf.dgii.gov.do/testecf";

    // Endpoints comunes
    private final String GENERAR_SEMILLA = "/autenticacion/api/autenticacion/semilla";
    private final String VALIDAR_SEMILLA = "/autenticacion/api/autenticacion/validarsemilla";

    public String getGenerarSemillaUrl(AmbienteEnum ambiente) {
        return getBaseUrl(ambiente) + GENERAR_SEMILLA;
    }

    public String getValidarSemillaUrl(AmbienteEnum ambiente) {
        return getBaseUrl(ambiente) + VALIDAR_SEMILLA;
    }

    private String getBaseUrl(AmbienteEnum ambiente) {
        return switch (ambiente) {
            case PRUEBAS -> BASE_URL_PRUEBAS;
            case CERTIFICACION -> BASE_URL_CERTIFICACION;
            case PRODUCCION -> BASE_URL_PRODUCCION;
        };
    }

}
