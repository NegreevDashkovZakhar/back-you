package it.me.backyou.repository;

/**
 * Repository interface for working with entries in database
 */
public interface IEntryRepository {
    /**
     * Method getting all entries from table
     *
     * @param tableName name of the table with entries
     * @return object containing data about all entries in table
     */
    Object getAllEntries(String tableName);

    /**
     * Method adding entry to the table
     *
     * @param tableName name of the table
     * @param columns   column names matching given values for new entry
     * @param values    values matching column names for new entry
     */
    void addEntry(String tableName, String[] columns, String[] values);

    /**
     * Method for removing entries with specified condition
     * All entries with specified value in specified column will be deleted
     *
     * @param tableName  name of the table
     * @param columnName name of the column for condition
     * @param value      value for condition
     */
    void removeEntries(String tableName, String columnName, String value);
}
