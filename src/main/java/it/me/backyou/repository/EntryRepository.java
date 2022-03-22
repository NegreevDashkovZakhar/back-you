package it.me.backyou.repository;

import it.me.backyou.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryRepository {
    private final QueryExecutor queryExecutor;

    @Autowired
    public EntryRepository(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public Object getAllEntries(final String tableName) {
        String sql = "SELECT * FROM " + tableName + ";";
        return queryExecutor.executeToObject(sql);
    }

    public void addEntry(final String tableName, final String[] columns, final String[] values) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ");
        sqlBuilder.append(tableName);
        sqlBuilder.append("(");
        for (int i = 0; i < columns.length - 1; i++) {
            sqlBuilder.append(columns[i]);
            sqlBuilder.append(", ");
        }
        sqlBuilder.append(columns[columns.length - 1]);
        sqlBuilder.append(") VALUES ('");
        for (int i = 0; i < values.length - 1; i++) {
            sqlBuilder.append(values[i]);
            sqlBuilder.append("', '");
        }
        sqlBuilder.append(values[values.length - 1]);
        sqlBuilder.append("');");
        queryExecutor.execute(sqlBuilder.toString());
    }

    public void removeEntries(final String tableName, final String columnName, final String value) {
        String sql = "DELETE FROM " + tableName
                + " WHERE " + columnName + "='" + value + "';";
        queryExecutor.execute(sql);
    }
}
