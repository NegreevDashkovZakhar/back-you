package it.me.backyou.service;

import it.me.backyou.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class
 * Holds logic for working with tables in database
 */
@Service
public class TableService {
    private final TableRepository tableRepository;

    /**
     * Default constructor setting table repository
     *
     * @param tableRepository table repository for working with database
     */
    @Autowired
    public TableService(final TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    /**
     * Method creating new table
     *
     * @param apiKey    user api key
     * @param tableName name of the new table
     */
    public void createTable(final String apiKey, final String tableName) {
        tableRepository.createTable(ServiceUtils.getTableName(apiKey, tableName));
    }

    /**
     * Method deleting the table
     *
     * @param apiKey    user api key
     * @param tableName name of the deleting table
     */
    public void dropTable(final String apiKey, final String tableName) {
        tableRepository.dropTable(ServiceUtils.getTableName(apiKey, tableName));
    }

    /**
     * Method renaming existing table
     *
     * @param apiKey  user api key
     * @param oldName old name of the table
     * @param newName new name of the table
     */
    public void renameTable(final String apiKey, final String oldName, final String newName) {
        String oldResult = ServiceUtils.getTableName(apiKey, oldName);
        String newResult = ServiceUtils.getTableName(apiKey, newName);
        tableRepository.renameTable(oldResult, newResult);
    }

    /**
     * Method getting columns data for table
     *
     * @param apiKey    user api key
     * @param tableName name of the needed table
     * @return object containing data about columns in the table
     */
    public Object getTableHeaderData(final String apiKey, final String tableName) {
        return tableRepository.getTableHeaderData(ServiceUtils.getTableName(apiKey, tableName));
    }

}
