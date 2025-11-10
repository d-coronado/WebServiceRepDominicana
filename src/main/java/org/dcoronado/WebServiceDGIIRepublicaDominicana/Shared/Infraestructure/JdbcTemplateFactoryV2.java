package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.Connection;

public class JdbcTemplateFactoryV2 {

    /**
     * Crea un JdbcTemplate que usa siempre la misma conexión (sin cerrar)
     */
    public JdbcTemplate fromConnection(Connection connection) {
        return new JdbcTemplate(new SingleConnectionDataSource(connection, true));
    }
}
