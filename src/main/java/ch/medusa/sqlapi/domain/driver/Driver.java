package ch.medusa.sqlapi.domain.driver;

public class Driver {

    private String description;
    private String name;

    public Driver() {
    }

    public Driver(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Driver setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Driver setName(String name) {
        this.name = name;
        return this;
    }
}
