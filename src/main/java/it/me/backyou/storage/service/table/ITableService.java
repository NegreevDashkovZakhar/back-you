package it.me.backyou.storage.service.table;

/**
 * Service interface holding logic for working with tables
 */
public interface ITableService {
    /**
     * Method creating new table
     *
     * @param apiKey    user api key
     * @param tableName name of the new table
     */
    void createTable(String apiKey, String tableName);

    /**
     * Method deleting the table
     *
     * @param apiKey    user api key
     * @param tableName name of the deleting table
     */
    void dropTable(String apiKey, String tableName);

    /**
     * Method renaming existing table
     *
     * @param apiKey  user api key
     * @param oldName old name of the table
     * @param newName new name of the table
     */
    void renameTable(String apiKey, String oldName, String newName);

    /**
     * Method getting columns data for table
     *
     * @param apiKey    user api key
     * @param tableName name of the needed table
     * @return object containing data about columns in the table
     */
    Object getTableHeaderData(String apiKey, String tableName);
}
