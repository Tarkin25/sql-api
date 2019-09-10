package ch.medusa.sqlapi.domain.repository;

import ch.medusa.sqlapi.domain.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DriverRepository {

    private List<Driver> drivers;

    public DriverRepository() {
        drivers = new ArrayList<>();

        drivers.add(new Driver("MariaDB Database Driver", "mariadb"));
        drivers.add(new Driver("MySQL Database Driver", "mysql"));
        drivers.add(new Driver("PostgreSQL Database Driver", "postgresql"));
        drivers.add(new Driver("Microsoft Server Database Driver", "sqlserver"));
    }

    public List<Driver> findAll() {
        return drivers;
    }
}
