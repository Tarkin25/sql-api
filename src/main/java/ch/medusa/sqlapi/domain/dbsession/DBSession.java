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

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public User getUser() {
        return user;
    }

    public DBSession setUser(User user) {
        this.user = user;
        return this;
    }
}
