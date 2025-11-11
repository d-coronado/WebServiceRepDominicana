package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.DbSetup;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DbConnectionInfo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.ScriptDataBaseExecutorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.JdbcTemplateFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.Util.FileSiytemDiscUtil.readFileFromResources;


@Component
@RequiredArgsConstructor
public class MYSQLScriptExecutorAdapter implements ScriptDataBaseExecutorPort {

    private static final String NAME_SCRIPT_SQL = "sql/schema_licencia_mysql.sql";
    private final JdbcTemplateFactory jdbcTemplateFactory;

    @Override
    public void executeScript(DbConnectionInfo data) {
        JdbcTemplate jdbcTemplate = jdbcTemplateFactory.create(data);
        String scriptSql = readFileFromResources(NAME_SCRIPT_SQL);

        for (String statement : scriptSql.split(";")) {
            String trimmed = statement.trim();
            if (!trimmed.isEmpty()) {
                jdbcTemplate.execute(trimmed);
            }
        }
    }
}
