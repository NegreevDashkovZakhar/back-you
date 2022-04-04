package it.me.backyou.service;

import it.me.backyou.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class
 * Holds logic for working with entries in database
 */
@Service
public class EntryService {
    private final EntryRepository entryRepository;

    /**
     * Default constructor setting entry repository for working with database
     *
     * @param entryRepository entry repository for working with database
     */
    @Autowired
    public EntryService(final EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    /**
     * Method for getting all entries from table
     *
     * @param apiKey    user api key
     * @param tableName name of the table
     * @return object containing data about all entries in the table
     */
    public Object getAllEntries(final String apiKey, final String tableName) {
        return entryRepository.getAllEntries(ServiceUtils.getTableName(apiKey, tableName));
    }

    /**
     * Method adding new entry to the table
     *
     * @param apiKey    user api key
     * @param tableName name of the talbe
     * @param columns   column names matching given values for new entry
     * @param values    values matching column names for new entry
     */
    public void addEntry(final String apiKey, final String tableName, final String[] columns, final String[] values) {
        entryRepository.addEntry(ServiceUtils.getTableName(apiKey, tableName), columns, values);
    }

    /**
     * Method deleting entries with specified condition
     * If entry has specified value in the specified column, it is deleted
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with entries
     * @param columnName name of the specified column for condition
     * @param value      specified value for condition
     */
    public void deleteEntries(final String apiKey, final String tableName, final String columnName,
                              final String value) {
        entryRepository.removeEntries(ServiceUtils.getTableName(apiKey, tableName), columnName, value);
    }
}
