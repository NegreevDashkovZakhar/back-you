package it.me.backyou.repository;

import it.me.backyou.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColumnRepository {
    private final QueryExecutor queryExecutor;

    @Autowired
    public ColumnRepository(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public void addColumn(final String tableName, final String columnName, final String columnType) {
        String sql = "ALTER TABLE " + tableName +
                " ADD COLUMN " + columnName + " " + columnType + ";";
        queryExecutor.execute(sql);
    }

    public void renameColumn(final String tableName, final String oldName, final String newName) {
        String sql = "ALTER TABLE " + tableName +
                " RENAME COLUMN " + oldName + " TO " + newName + ";";
        queryExecutor.execute(sql);
    }

    public void deleteColumn(final String tableName, final String columnName) {
        String sql = "ALTER TABLE " + tableName +
                " DROP COLUMN " + columnName + ";";
        queryExecutor.execute(sql);
    }

    public void changeColumnType(final String tableName, final String columnName, final String newType) {
        String sql = "ALTER TABLE " + tableName +
                " ALTER COLUMN " + columnName + " TYPE " + newType +
                " USING " + columnName + "::" + newType + ";";
        queryExecutor.execute(sql);
    }

    public void changeColumnDefault(final String tableName, final String columnName, final String defaultValue) {
        String sql = "ALTER TABLE " + tableName +
                " ALTER COLUMN " + columnName + " SET DEFAULT '" + defaultValue + "';";
        queryExecutor.execute(sql);
    }
}
