package ch.medusa.sqlapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SqlApiApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(SqlApiApplication.class, args);
    }

}
