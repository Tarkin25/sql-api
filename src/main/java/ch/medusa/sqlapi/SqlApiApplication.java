package ch.medusa.sqlapi;

import ch.medusa.sqlapi.domain.sql.ConnectorService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlApiApplication implements CommandLineRunner {

    private Logger logger;
    private ConnectorService connectorService;

    @Autowired
    public SqlApiApplication(Logger logger, ConnectorService connectorService) {
        this.logger = logger;
        this.connectorService = connectorService;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    public static void main(String[] args) {
        SpringApplication.run(SqlApiApplication.class, args);
    }

}
