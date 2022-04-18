package it.me.backyou.storage.service.entry;

/**
 * Service interface holding logic for working with entries
 */
public interface IEntryService {
    /**
     * Method for getting all entries from table
     *
     * @param apiKey    user api key
     * @param tableName name of the table
     * @return object containing data about all entries in the table
     */
    Object getAllEntries(String apiKey, String tableName);

    /**
     * Method adding new entry to the table
     *
     * @param apiKey    user api key
     * @param tableName name of the talbe
     * @param columns   column names matching given values for new entry
     * @param values    values matching column names for new entry
     */
    void addEntry(String apiKey, String tableName, String[] columns, String[] values);

    /**
     * Method deleting entries with specified condition
     * If entry has specified value in the specified column, it is deleted
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with entries
     * @param columnName name of the specified column for condition
     * @param value      specified value for condition
     */
    void deleteEntries(String apiKey, String tableName, String columnName,
                       String value);
}
