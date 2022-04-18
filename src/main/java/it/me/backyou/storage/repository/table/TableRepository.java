package it.me.backyou.storage.repository.table;

import it.me.backyou.storage.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Repository class
 * Executes sql queries for working with tables
 */
@Component
public class TableRepository implements ITableRepository {
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

    @Override
    public void createTable(final String tableName) {
        String sql = "CREATE TABLE " + tableName + "(" +
                "id serial PRIMARY KEY" +
                ");";
        queryExecutor.execute(sql);
    }

    @Override
    public void dropTable(final String tableName) {
        String sql = "DROP TABLE " + tableName + ";";
        queryExecutor.execute(sql);
    }

    @Override
    public void renameTable(final String oldName, final String newName) {
        String sql = "ALTER TABLE " + oldName +
                " RENAME TO " + newName + ";";
        queryExecutor.execute(sql);
    }

    @Override
    public Object getTableHeaderData(final String tableName) {
        String sql = "SELECT column_name, data_type, column_default " +
                "FROM information_schema.columns " +
                "WHERE table_name = '" + tableName + "';";
        return queryExecutor.executeToObject(sql);
    }
}
