package ch.medusa.sqlapi.domain.model;

import ch.medusa.sqlapi.config.validation.Driver;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Credentials {

    @Driver
    @NotNull
    private String databaseManagementSystem;

    @NotBlank
    private String hostname;

    @Min(0)
    private Integer port;

    @NotBlank
    private String user;

    @NotBlank
    private String password;

    private String database;

    public String getDatabaseManagementSystem() {
        return databaseManagementSystem;
    }

    public void setDatabaseManagementSystem(String databaseManagementSystem) {
        this.databaseManagementSystem = databaseManagementSystem;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
