package ch.medusa.sqlapi.domain.sql;

import ch.medusa.sqlapi.domain.dbsession.DBSession;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class ConnectorService {

    public Connection getConnection(DBSession DBSession) throws SQLException {
        Properties props = new Properties();
        props.put("user", DBSession.getUsername());
        props.put("password", DBSession.getPassword());

        return DriverManager.getConnection(jdbcUrl(DBSession), props);
    }

    private String jdbcUrl(DBSession c) {
        String database = c.getDatabase();

        if(database != null) {
            return String.format("jdbc:%s://%s:%d/%s", c.getDatabaseManagementSystem(), c.getHostname(), c.getPort(), database);
        } else {
            return String.format("jdbc:%s://%s:%d", c.getDatabaseManagementSystem(), c.getHostname(), c.getPort());
        }
    }
}
