package ch.medusa.sqlapi.domain.service;

import ch.medusa.sqlapi.domain.model.resultanalysis.Column;
import ch.medusa.sqlapi.domain.model.resultanalysis.Row;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultAnalyzerService {

    public Object analyzeResultSet(ResultSet resultSet) throws SQLException {
        Map<String, Object> map = new LinkedHashMap<>();

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        // get columns
        List<Column> columns = new ArrayList<>();

        for(int i=1;i<=resultSetMetaData.getColumnCount();i++) {
            columns.add(new Column(resultSetMetaData.getColumnName(i), resultSetMetaData.getColumnTypeName(i)));
        }

        map.put("columns", columns);

        // get rows
        List<Row> rows = new ArrayList<>();

        while(resultSet.next()) {
            Row row = new Row();

            for(int i=1;i<=resultSetMetaData.getColumnCount();i++) {
                Column column = columns.get(i-1);

                row.put(column.getName(), resultSet.getObject(i));
            }

            rows.add(row);
        }

        map.put("rows", rows);

        return map;
    }

}
