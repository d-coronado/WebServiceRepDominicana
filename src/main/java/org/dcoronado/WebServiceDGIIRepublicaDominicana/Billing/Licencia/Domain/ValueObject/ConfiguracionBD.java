package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.RNC;

import java.util.UUID;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Getter
@EqualsAndHashCode
public class ConfiguracionBD {

    private final String nombreBD;
    private final String hostBD;
    private final String puertoBD;
    private final String usuario;
    private final String password;
    private final String urlConexion;

    private ConfiguracionBD(String nombreBD, String hostBD, String puertoBD, String usuario,
                            String password, String urlConexion, boolean skipValidation) {
        if (!skipValidation) {
            notBlank(nombreBD, "Nombre BD requerido");
            notBlank(hostBD, "Host BD requerido");
            notBlank(puertoBD, "Puerto BD requerido");
            notBlank(usuario, "Usuario BD requerido");
            notBlank(password, "Password BD requerido");
        }
        this.nombreBD = nombreBD;
        this.hostBD = hostBD;
        this.puertoBD = puertoBD;
        this.usuario = usuario;
        this.password = password;
        this.urlConexion = urlConexion;
    }

    // Fábrica de negocio (genera configuración automáticamente)
    public static ConfiguracionBD generar(RNC rnc, String host, String puerto) {
        notBlank(rnc.getValor(), "RNC requerido");
        notBlank(host, "Host requerido");
        notBlank(puerto, "Puerto requerido");

        String nombreBD = "fe_rd_V2_" + rnc.getValor();
        String usuario = "fe_rd_user_V2_" + rnc.getValor();
        String password = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12);
        String urlConexion = String.format("jdbc:mysql://%s:%s/%s", host, puerto, nombreBD);

        return new ConfiguracionBD(nombreBD, host, puerto, usuario, password, urlConexion, false);
    }

    // Fábrica de reconstrucción desde BD
    public static ConfiguracionBD reconstruirDesdeBD(String nombreBD, String hostBD, String puertoBD,
                                                     String usuario, String password, String urlConexion) {
        return new ConfiguracionBD(nombreBD, hostBD, puertoBD, usuario, password, urlConexion, true);
    }

    public boolean existe() {
        return urlConexion != null && this.usuario != null & this.password != null; // campos clave
    }
}
