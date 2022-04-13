package it.me.backyou.repository.table;

/**
 * Repository interface for working with tables in database
 */
public interface ITableRepository {
    /**
     * Method creating new table
     *
     * @param tableName name of the new table
     */
    void createTable(String tableName);

    /**
     * Method deleting existing table
     *
     * @param tableName name of the deleting table
     */
    void dropTable(String tableName);

    /**
     * Method renaming existing table
     *
     * @param oldName old name of the table
     * @param newName new name for the table
     */
    void renameTable(String oldName, String newName);

    /**
     * Method getting data about table columns
     *
     * @param tableName name of the table
     * @return object with data about table columns
     */
    Object getTableHeaderData(String tableName);
}
