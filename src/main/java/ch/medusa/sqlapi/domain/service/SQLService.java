package ch.medusa.sqlapi.domain.service;

import ch.medusa.sqlapi.domain.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class SQLService {

    private ConnectorService connectorService;

    @Autowired
    public SQLService(ConnectorService connectorService) {
        this.connectorService = connectorService;
    }

    public String executeQuery(Credentials credentials, String sql) throws SQLException {
        Connection conn = connectorService.getConnection(credentials);

        ResultSet rs = conn.createStatement().executeQuery(sql);

        StringBuilder sb = new StringBuilder();

        int columns = rs.getMetaData().getColumnCount();

        while(rs.next()) {
            for(int i=1;i<=columns;i++) {
                sb.append(rs.getString(i)).append(", ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
