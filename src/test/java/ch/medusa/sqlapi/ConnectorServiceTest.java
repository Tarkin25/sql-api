package ch.medusa.sqlapi;

import ch.medusa.sqlapi.domain.dbsession.DBSession;
import ch.medusa.sqlapi.domain.sql.ConnectorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConnectorServiceTest {

    @Autowired
    private ConnectorService connectorService;

    @Test
    public void mariadbConnectionTest() {

        DBSession session = new DBSession()
                .setHostname("localhost")
                .setPort(3306)
                .setUsername("root")
                .setPassword("root")
                .setDatabaseManagementSystem("mariadb");

        testConnection(session, "MariaDB");
    }

    @Test
    public void postgresConnectionTest() {
        DBSession session = new DBSession()
                .setHostname("localhost")
                .setPort(5432)
                .setUsername("postgres")
                .setPassword("postgres")
                .setDatabaseManagementSystem("postgresql")
                .setDatabase("postgres");

        testConnection(session, "PostgreSQL");
    }

    @Test
    public void mysqlConnectionTest() {
        DBSession session = new DBSession()
                .setHostname("localhost")
                .setPort(3306)
                .setUsername("root")
                .setPassword("root")
                .setDatabaseManagementSystem("mysql")
                .setDatabase("mysql");

        testConnection(session, "MySQL");
    }

    @Test
    public void sqlserverConnectionTest() {
        DBSession session = new DBSession()
                .setHostname("localhost")
                .setPort(3306)
                .setUsername("root")
                .setPassword("root")
                .setDatabaseManagementSystem("sqlserver")
                .setDatabase("mysql");

        testConnection(session, "SQLServer");
    }

    private void testConnection(DBSession dbSession, String expectedDatabaseName) {
        try {
            Connection connection = connectorService.getConnection(dbSession);

            String databaseProductName = connection.getMetaData().getDatabaseProductName();

            System.out.printf("Database name is '%s'", databaseProductName);

            Assert.assertEquals(expectedDatabaseName, databaseProductName);
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        }
    }
}
