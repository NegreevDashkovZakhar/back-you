package it.me.backyou.repository;

import it.me.backyou.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Repository class
 * Executes sql queries for working with tables
 */
@Component
public class TableRepository {
    private final QueryExecutor queryExecutor;

    /**
     * Default constructor setting query executor
     *
     * @param queryExecutor query executor for executing sql queries
     */
    @Autowired
    public TableRepository(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    /**
     * Method creating new table
     *
     * @param tableName name of the new table
     */
    public void createTable(final String tableName) {
        String sql = "CREATE TABLE " + tableName + "(" +
                "id serial PRIMARY KEY" +
                ");";
        queryExecutor.execute(sql);
    }

    /**
     * Method deleting existing table
     *
     * @param tableName name of the deleting table
     */
    public void dropTable(final String tableName) {
        String sql = "DROP TABLE " + tableName + ";";
        queryExecutor.execute(sql);
    }

    /**
     * Method renaming existing table
     *
     * @param oldName old name of the table
     * @param newName new name for the table
     */
    public void renameTable(final String oldName, final String newName) {
        String sql = "ALTER TABLE " + oldName +
                " RENAME TO " + newName + ";";
        queryExecutor.execute(sql);
    }

    /**
     * Method getting data about table columns
     *
     * @param tableName name of the table
     * @return object with data about table columns
     */
    public Object getTableHeaderData(final String tableName) {
        String sql = "SELECT column_name, data_type, column_default " +
                "FROM information_schema.columns " +
                "WHERE table_name = '" + tableName + "';";
        return queryExecutor.executeToObject(sql);
    }
}
