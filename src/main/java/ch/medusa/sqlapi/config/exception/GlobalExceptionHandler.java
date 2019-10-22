package ch.medusa.sqlapi.config.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger;

    @Autowired
    public GlobalExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> exception(Exception e) {
        logger.debug("Exception occurred: ", e);
        logger.debug("Exception message was sent to the client");

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> sqlException(SQLException e) {
        logger.debug("SQLException occurred: ", e);
        logger.debug("Exception message was sent to the client");

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        map.put("error_code", e.getErrorCode());
        map.put("sql_state", e.getSQLState());
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> noSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<String> permissionDeniedException(PermissionDeniedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
