package ch.medusa.sqlapi.domain.service;

import ch.medusa.sqlapi.domain.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@Service
public class SQLService {

    private ConnectorService connectorService;
    private ResultAnalyzerService analyzerService;

    @Autowired
    public SQLService(ConnectorService connectorService, ResultAnalyzerService analyzerService) {
        this.connectorService = connectorService;
        this.analyzerService = analyzerService;
    }

    public Object executeQuery(Credentials credentials, String sql) throws SQLException {
        Connection conn = connectorService.getConnection(credentials);

        Object analyzedResultSet = analyzerService.analyzeResultSet(conn.prepareStatement(sql).executeQuery());

        conn.close();

        return analyzedResultSet;
    }

    public void runScript(Credentials credentials, MultipartFile multipartFile) throws IOException, SQLException {
        Connection conn = connectorService.getConnection(credentials);

        Scanner scanner = new Scanner(multipartFile.getInputStream());

        while(scanner.hasNextLine()) {
            conn.prepareStatement(scanner.nextLine()).execute();
        }
    }
}
