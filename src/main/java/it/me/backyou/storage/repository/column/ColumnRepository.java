package it.me.backyou.storage.repository.column;

import it.me.backyou.storage.repository.query.QueryExecutor;
import it.me.backyou.storage.service.column.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class executing queries for columns
 * Used in column service
 *
 * @see ColumnService
 */
@Component
public class ColumnRepository implements IColumnRepository {
    private final QueryExecutor queryExecutor;

    /**
     * Default constructor setting query executor
     *
     * @param queryExecutor query executor for repository
     */
    @Autowired
    public ColumnRepository(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    @Override
    public void addColumn(final String tableName, final String columnName, final String columnType) {
        String sql = "ALTER TABLE " + tableName +
                " ADD COLUMN " + columnName + " " + columnType + ";";
        queryExecutor.execute(sql);
    }

    @Override
    public void renameColumn(final String tableName, final String oldName, final String newName) {
        String sql = "ALTER TABLE " + tableName +
                " RENAME COLUMN " + oldName + " TO " + newName + ";";
        queryExecutor.execute(sql);
    }

    @Override
    public void deleteColumn(final String tableName, final String columnName) {
        String sql = "ALTER TABLE " + tableName +
                " DROP COLUMN " + columnName + ";";
        queryExecutor.execute(sql);
    }

    @Override
    public void changeColumnType(final String tableName, final String columnName, final String newType) {
        String sql = "ALTER TABLE " + tableName +
                " ALTER COLUMN " + columnName + " TYPE " + newType +
                " USING " + columnName + "::" + newType + ";";
        queryExecutor.execute(sql);
    }

    @Override
    public void changeColumnDefault(final String tableName, final String columnName, final String defaultValue) {
        String sql = "ALTER TABLE " + tableName +
                " ALTER COLUMN " + columnName + " SET DEFAULT '" + defaultValue + "';";
        queryExecutor.execute(sql);
    }
}
