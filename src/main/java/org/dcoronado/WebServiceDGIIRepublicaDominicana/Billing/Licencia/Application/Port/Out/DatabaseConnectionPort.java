package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DatabaseConnectionPort {

    /**
     * Ejecuta INSERT/UPDATE/DELETE.
     */
    int update(Licencia licencia, String sql, Object... params);

    /**
     * Lee una fila.
     */
    Optional<Map<String, Object>> queryOne(Licencia licencia, String sql, Object... params);

    /**
     * Lee varias filas.
     */
    List<Map<String, Object>> queryMany(Licencia licencia, String sql, Object... params);

}
