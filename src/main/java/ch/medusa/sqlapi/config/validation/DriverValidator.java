package ch.medusa.sqlapi.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class DriverValidator implements ConstraintValidator<Driver, String> {

    private List<String> availableDrivers;

    public DriverValidator() {
        availableDrivers = new ArrayList<>();

        availableDrivers.add("mariadb");
        availableDrivers.add("postgresql");
        availableDrivers.add("mysql");
        availableDrivers.add("sqlserver");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return availableDrivers.contains(value);
    }
}
