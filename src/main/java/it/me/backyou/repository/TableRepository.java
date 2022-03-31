package it.me.backyou.repository;

import it.me.backyou.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableRepository {
    private final QueryExecutor queryExecutor;

    @Autowired
    public TableRepository(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public void createTable(final String tableName) {
        String sql = "CREATE TABLE " + tableName + "(" +
                "id serial PRIMARY KEY" +
                ");";
        queryExecutor.execute(sql);
    }

    public void dropTable(final String tableName) {
        String sql = "DROP TABLE " + tableName + ";";
        queryExecutor.execute(sql);
    }

    public void renameTable(final String oldName, final String newName) {
        String sql = "ALTER TABLE " + oldName +
                " RENAME TO " + newName + ";";
        queryExecutor.execute(sql);
    }

    public Object getTableHeaderData(final String tableName) {
        String sql = "SELECT column_name, data_type, column_default " +
                "FROM information_schema.columns " +
                "WHERE table_name = '" + tableName + "';";
        return queryExecutor.executeToObject(sql);
    }
}
