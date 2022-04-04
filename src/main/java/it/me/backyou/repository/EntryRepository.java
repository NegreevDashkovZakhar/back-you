package it.me.backyou.repository;

import it.me.backyou.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Repository class
 * Executes sql queries for working with entries
 */
@Component
public class EntryRepository {
    private final QueryExecutor queryExecutor;

    /**
     * Default constructor setting query executor
     *
     * @param queryExecutor query executor for executing sql queries
     */
    @Autowired
    public EntryRepository(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    /**
     * Method getting all entries from table
     *
     * @param tableName name of the table with entries
     * @return object containing data about all entries in table
     */
    public Object getAllEntries(final String tableName) {
        String sql = "SELECT * FROM " + tableName + ";";
        return queryExecutor.executeToObject(sql);
    }

    /**
     * Method adding entry to the table
     *
     * @param tableName name of the table
     * @param columns   column names matching given values for new entry
     * @param values    values matching column names for new entry
     */
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

    /**
     * Method for removing entries with specified condition
     * All entries with specified value in specified column will be deleted
     *
     * @param tableName  name of the table
     * @param columnName name of the column for condition
     * @param value      value for condition
     */
    public void removeEntries(final String tableName, final String columnName, final String value) {
        String sql = "DELETE FROM " + tableName
                + " WHERE " + columnName + "='" + value + "';";
        queryExecutor.execute(sql);
    }
}
