package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DynamicConnectionManager {

    // Todas las BDs de licencias se van a manejar en el motor MySql.
    public JdbcTemplate createJdbcTemplate(String dbUrl, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new JdbcTemplate(dataSource);
    }
}
