package it.me.backyou.repository;

import it.me.backyou.repository.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class executing queries for columns
 * Used in column service
 *
 * @see it.me.backyou.service.ColumnService
 */
@Component
public class ColumnRepository {
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

    /**
     * Method executing add column query
     *
     * @param tableName  final name of the table
     * @param columnName name of the new column
     * @param columnType type of the new column
     */
    public void addColumn(final String tableName, final String columnName, final String columnType) {
        String sql = "ALTER TABLE " + tableName +
                " ADD COLUMN " + columnName + " " + columnType + ";";
        queryExecutor.execute(sql);
    }

    /**
     * Method executing rename column query
     *
     * @param tableName final name of the table
     * @param oldName   old name of the column
     * @param newName   new name for the column
     */
    public void renameColumn(final String tableName, final String oldName, final String newName) {
        String sql = "ALTER TABLE " + tableName +
                " RENAME COLUMN " + oldName + " TO " + newName + ";";
        queryExecutor.execute(sql);
    }

    /**
     * Method executing delete column query
     *
     * @param tableName  final name of the table
     * @param columnName name of the column that will be deleted
     */
    public void deleteColumn(final String tableName, final String columnName) {
        String sql = "ALTER TABLE " + tableName +
                " DROP COLUMN " + columnName + ";";
        queryExecutor.execute(sql);
    }

    /**
     * Method executing change column type query
     *
     * @param tableName  final name of the table
     * @param columnName name of the column, type of which will be changed
     * @param newType    new type of the specified column
     */
    public void changeColumnType(final String tableName, final String columnName, final String newType) {
        String sql = "ALTER TABLE " + tableName +
                " ALTER COLUMN " + columnName + " TYPE " + newType +
                " USING " + columnName + "::" + newType + ";";
        queryExecutor.execute(sql);
    }

    /**
     * Method executing change column default query.
     * Sets default value for column
     *
     * @param tableName    final name of the table
     * @param columnName   name of the column which default value will be set
     * @param defaultValue value that will be set as default for specified column
     */
    public void changeColumnDefault(final String tableName, final String columnName, final String defaultValue) {
        String sql = "ALTER TABLE " + tableName +
                " ALTER COLUMN " + columnName + " SET DEFAULT '" + defaultValue + "';";
        queryExecutor.execute(sql);
    }
}
