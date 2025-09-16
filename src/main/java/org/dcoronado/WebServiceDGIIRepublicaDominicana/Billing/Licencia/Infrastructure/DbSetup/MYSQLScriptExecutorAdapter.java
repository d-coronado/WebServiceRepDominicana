package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.DbSetup;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.ScriptDataBaseExecutorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.DynamicConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.readFileFromResources;


@Component
@RequiredArgsConstructor
public class MYSQLScriptExecutorAdapter implements ScriptDataBaseExecutorPort {

    private static final String NAME_SCRIPT_SQL = "sql/schema_licencia_mysql.sql";

    private final DynamicConnectionManager connectionManager;

    @Override
    public void executeScript(Licencia licencia) {

        JdbcTemplate jdbcTemplate = connectionManager.createJdbcTemplate(licencia.getUrlConexionBd(), licencia.getUsuarioBd(), licencia.getPasswordBd());

        String scriptSql = readFileFromResources(NAME_SCRIPT_SQL);

        for (String statement : scriptSql.split(";")) {
            String trimmed = statement.trim();
            if (!trimmed.isEmpty()) {
                jdbcTemplate.execute(trimmed);
            }
        }
    }

}
