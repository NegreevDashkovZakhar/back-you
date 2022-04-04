package it.me.backyou.service.column;

/**
 * Service interface holding logic for working with columns
 */
public interface IColumnService {
    /**
     * Method adding column to database
     *
     * @param apiKey     user api key
     * @param tableName  name of the table where new column will be added
     * @param columnName name of the new column
     * @param columnType type of the new column
     */
    void addColumn(String apiKey, String tableName, String columnName,
                   String columnType);

    /**
     * Method renaming existing column
     *
     * @param apiKey    user api key
     * @param tableName name of the table with column
     * @param oldName   old name of the column
     * @param newName   new name of the column
     */
    void renameColumn(String apiKey, String tableName, String oldName, String newName);

    /**
     * Method deleting existing column
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with column
     * @param columnName name of the deleting column
     */
    void deleteColumn(String apiKey, String tableName, String columnName);

    /**
     * Method changing existing column type
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with column
     * @param columnName name of the column
     * @param newType    new ttype of the specified column
     */
    void changeColumnType(String apiKey, String tableName, String columnName,
                          String newType);

    /**
     * Method setting default value for column
     *
     * @param apiKey       user api key
     * @param tableName    name of the table with the column
     * @param columnName   name of the column
     * @param defaultValue new default value for specified column
     */
    void changeColumnDefault(String apiKey, String tableName, String columnName,
                             String defaultValue);
}
