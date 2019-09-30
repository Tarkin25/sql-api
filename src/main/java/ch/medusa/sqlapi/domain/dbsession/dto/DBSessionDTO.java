package ch.medusa.sqlapi.domain.dbsession.dto;

public class DBSessionDTO {

    private String id;
    private String databaseManagementSystem;
    private String hostname;
    private Integer port;
    private String username;
    private String password;
    private String database;

    public String getId() {
        return id;
    }

    public DBSessionDTO setId(String id) {
        this.id = id;
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
