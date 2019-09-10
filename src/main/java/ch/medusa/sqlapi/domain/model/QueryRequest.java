package ch.medusa.sqlapi.domain.model;

import java.util.List;

public class QueryRequest {

    private Credentials credentials;

    private String sql;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
