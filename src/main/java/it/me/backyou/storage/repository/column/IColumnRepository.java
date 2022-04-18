package it.me.backyou.storage.repository.column;

/**
 * Interface for repository working with database on columns
 */
public interface IColumnRepository {
    /**
     * Method executing add column query
     *
     * @param tableName  final name of the table
     * @param columnName name of the new column
     * @param columnType type of the new column
     */
    void addColumn(String tableName, String columnName, String columnType);

    /**
     * Method executing rename column query
     *
     * @param tableName final name of the table
     * @param oldName   old name of the column
     * @param newName   new name for the column
     */
    void renameColumn(String tableName, String oldName, String newName);

    /**
     * Method executing delete column query
     *
     * @param tableName  final name of the table
     * @param columnName name of the column that will be deleted
     */
    void deleteColumn(String tableName, String columnName);

    /**
     * Method executing change column type query
     *
     * @param tableName  final name of the table
     * @param columnName name of the column, type of which will be changed
     * @param newType    new type of the specified column
     */
    void changeColumnType(String tableName, String columnName, String newType);

    /**
     * Method executing change column default query.
     * Sets default value for column
     *
     * @param tableName    final name of the table
     * @param columnName   name of the column which default value will be set
     * @param defaultValue value that will be set as default for specified column
     */
    void changeColumnDefault(String tableName, String columnName, String defaultValue);
}
