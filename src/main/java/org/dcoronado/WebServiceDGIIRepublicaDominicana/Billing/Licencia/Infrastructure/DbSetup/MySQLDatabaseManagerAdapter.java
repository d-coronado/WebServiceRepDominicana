package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.DbSetup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseManagerPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseSetupData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MySQLDatabaseManagerAdapter implements DatabaseManagerPort {

    private static final String SQL_CREATE_DB = "CREATE DATABASE IF NOT EXISTS `%s` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";
    private static final String SQL_CREATE_USER = "CREATE USER IF NOT EXISTS '%s'@'%s' IDENTIFIED BY '%s';";
    private static final String SQL_GRANT_PRIVILEGES = "GRANT ALL PRIVILEGES ON `%s`.* TO '%s'@'%s';";
    private static final String SQL_FLUSH_PRIVILEGES = "FLUSH PRIVILEGES;";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String execute(DatabaseSetupData data) {
        createDatabase(data.nombreBd());
        createUser(data.usuarioBd(), data.passwordBd(), data.host());
        grantPrivileges(data.nombreBd(), data.usuarioBd(), data.host());
        String url = String.format("jdbc:mysql://%s:%s/%s", data.host(), data.puerto(), data.nombreBd());
        log.info("Setup completado para licencia con RNC: {}", data.rnc());
        return url;
    }

    private void createDatabase(String dbName) {
        String sql = String.format(SQL_CREATE_DB, dbName);
        jdbcTemplate.execute(sql);
        log.debug("Base de datos creada: {}", dbName);
    }

    private void createUser(String username, String password, String host) {
        String sql = String.format(SQL_CREATE_USER, username, host, password);
        jdbcTemplate.execute(sql);
        log.debug("Usuario creado: {} para host: {}", username, host);
    }

    private void grantPrivileges(String dbName, String username, String host) {
        String grantSql = String.format(SQL_GRANT_PRIVILEGES, dbName, username, host);
        jdbcTemplate.execute(grantSql);
        jdbcTemplate.execute(SQL_FLUSH_PRIVILEGES);
        log.debug("Privilegios otorgados a usuario {} sobre {} para host: {}", username, dbName, host);
    }


}
