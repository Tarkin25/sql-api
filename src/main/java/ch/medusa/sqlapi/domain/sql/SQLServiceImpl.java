package ch.medusa.sqlapi.domain.sql;

import ch.medusa.sqlapi.domain.dbsession.DBSession;
import ch.medusa.sqlapi.domain.dbsession.DBSessionService;
import ch.medusa.sqlapi.domain.resultanalysis.ResultAnalyzerService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.*;

@Service
public class SQLServiceImpl implements SQLService {

    private ConnectorService connectorService;
    private ResultAnalyzerService analyzerService;
    private Logger logger;
    private DBSessionService DBSessionService;

    @Autowired
    public SQLServiceImpl(ConnectorService connectorService, ResultAnalyzerService analyzerService, Logger logger, DBSessionService DBSessionService) {
        this.connectorService = connectorService;
        this.analyzerService = analyzerService;
        this.logger = logger;
        this.DBSessionService = DBSessionService;
    }

    @Override
    public Map<String, Object> executeQuery(String credentialsId, String sql) throws SQLException {
        DBSession DBSession = DBSessionService.findById(credentialsId);

        Connection conn = connectorService.getConnection(DBSession);

        Map<String, Object> analyzedResultSet = analyzerService.analyzeResultSet(conn.prepareStatement(sql).executeQuery());

        conn.close();

        return analyzedResultSet;
    }

    @Override
    public void runScript(String credentialsId, MultipartFile multipartFile) throws IOException, SQLException {
        DBSession DBSession = DBSessionService.findById(credentialsId);

        Connection conn = connectorService.getConnection(DBSession);

        logger.debug("Set autocommit to false");
        conn.setAutoCommit(false);

        logger.debug("Set savepoint");
        Savepoint savepoint = conn.setSavepoint();

        logger.debug("Beginning transaction");

        try {
            for(String statement : parseStatements(multipartFile)) {
                logger.debug("Executing statement '{}'", statement);
                conn.prepareStatement(statement).execute();
            }

            logger.debug("Committing");
            conn.commit();
        } catch (SQLException e) {
            logger.debug("Rolling back due to an Exception while executing statements");

            conn.rollback();

            logger.debug("Throwing the Exception");
            throw e;
        }
    }

    private List<String> parseStatements(String query) {
        List<String> statements = new ArrayList<>();

        String[] split = query.split(";");

        for(String statement : split) {
            statements.add(statement + ";");
        }

        return statements;
    }

    private List<String> parseStatements(MultipartFile file) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(file.getInputStream());

        logger.debug("Reading script:");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(!line.isEmpty() && !line.isBlank() && !line.startsWith("#")) {
                logger.debug("ADDED '{}' to the statements to be executed", line);

                sb.append(line);
            } else {
                logger.debug("IGNORED '{}' because the line was empty, blank or commented out", line);
            }
        }

        return parseStatements(sb.toString());
    }
}
