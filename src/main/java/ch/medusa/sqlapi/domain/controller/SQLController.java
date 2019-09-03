package ch.medusa.sqlapi.domain.controller;

import ch.medusa.sqlapi.domain.model.QueryRequest;
import ch.medusa.sqlapi.domain.service.SQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/sql")
public class SQLController {

    private SQLService sqlService;

    @Autowired
    public SQLController(SQLService sqlService) {
        this.sqlService = sqlService;
    }

    @PostMapping
    public ResponseEntity<String> sqlQuery(@RequestBody QueryRequest queryRequest) throws SQLException {
        String result = sqlService.executeQuery(queryRequest.getCredentials(), queryRequest.getSql());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
