package ch.medusa.sqlapi.domain.dbsession.dto;

import ch.medusa.sqlapi.config.validation.Driver;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DBSessionDTO {

    private String id;

    @NotBlank
    private String name;

    @NotNull @Driver
    private String databaseManagementSystem;

    @NotBlank
    private String hostname;

    @Min(0)
    private Integer port;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String database;

    public String getId() {
        return id;
    }

    public DBSessionDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DBSessionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDatabaseManagementSystem() {
        return databaseManagementSystem;
    }

    public DBSessionDTO setDatabaseManagementSystem(String databaseManagementSystem) {
        this.databaseManagementSystem = databaseManagementSystem;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public DBSessionDTO setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public DBSessionDTO setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DBSessionDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DBSessionDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public DBSessionDTO setDatabase(String database) {
        this.database = database;
        return this;
    }
}
