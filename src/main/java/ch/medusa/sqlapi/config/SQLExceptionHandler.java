package ch.medusa.sqlapi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class SQLExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlException(SQLException e) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("error_code", e.getErrorCode());
        map.put("sql_state", e.getSQLState());
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}
