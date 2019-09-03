package ch.medusa.sqlapi.domain.service;

import ch.medusa.sqlapi.domain.model.Credentials;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class ConnectorService {

    public Connection getConnection(Credentials credentials) throws SQLException {
        Properties props = new Properties();
        props.put("user", credentials.getUser());
        props.put("password", credentials.getPassword());

        return DriverManager.getConnection(jdbcUrl(credentials), props);
    }

    private String jdbcUrl(Credentials c) {
        String database = c.getDatabase();

        if(database != null) {
            return String.format("jdbc:%s://%s:%d/%s", c.getDatabaseManagementSystem(), c.getHostname(), c.getPort(), database);
        } else {
            return String.format("jdbc:%s://%s:%d", c.getDatabaseManagementSystem(), c.getHostname(), c.getPort());
        }
    }
}
