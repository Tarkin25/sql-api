package ch.medusa.sqlapi.domain.sql;

public class QueryRequest {

    private String query;

    public String getQuery() {
        return query;
    }

    public QueryRequest setQuery(String query) {
        this.query = query;
        return this;
    }
}
