package ch.medusa.sqlapi.domain.sql;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface SQLService {

    Map<String, Object> executeQuery(String credentialsId, String query) throws SQLException;

    void runScript(String credentialsId, MultipartFile script) throws IOException, SQLException;

}
