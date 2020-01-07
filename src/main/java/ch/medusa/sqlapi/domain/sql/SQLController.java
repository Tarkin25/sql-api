package ch.medusa.sqlapi.domain.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/sql")
public class SQLController {

    private SQLService sqlService;

    @Autowired
    public SQLController(SQLService sqlService) {
        this.sqlService = sqlService;
    }

    @PostMapping("/query")
    public ResponseEntity<Map<String, Object>> executeQuery(
            @RequestHeader("DB-Session") String credentialsId,
            @RequestBody QueryRequest queryRequest
    ) throws SQLException {
        return new ResponseEntity<>(sqlService.executeQuery(credentialsId, queryRequest.getQuery()), HttpStatus.OK);
    }

    @PostMapping("/script")
    public ResponseEntity<Void> runScript(
            @RequestHeader("DB-Session") String credentialsId,
            @RequestParam("script") MultipartFile script
            ) throws IOException, SQLException {
        sqlService.runScript(credentialsId, script);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
