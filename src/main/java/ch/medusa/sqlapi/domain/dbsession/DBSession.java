package ch.medusa.sqlapi.domain.dbsession;

import ch.medusa.sqlapi.config.validation.Driver;
import ch.medusa.sqlapi.domain.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "credentials")
public class DBSession {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Driver
    @NotNull
    @Column(name = "database_management_system")
    private String databaseManagementSystem;

    @NotBlank
    private String hostname;

    @Min(0)
    private Integer port;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Column(name = "database_name")
    private String database;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public String getId() {
        return id;
    }

    public DBSession setId(String id) {
        this.id = id;
        return this;
    }

    public String getDatabaseManagementSystem() {
        return databaseManagementSystem;
    }

    public DBSession setDatabaseManagementSystem(String databaseManagementSystem) {
        this.databaseManagementSystem = databaseManagementSystem;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public DBSession setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public DBSession setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DBSession setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DBSession setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public DBSession setDatabase(String database) {
        this.database = database;
        return this;
    }

    public User getUser() {
        return user;
    }

    public DBSession setUser(User user) {
        this.user = user;
        return this;
    }
}
