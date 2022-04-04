package it.me.backyou.service;

import it.me.backyou.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class
 * Holds logic for executing queries for columns
 */
@Service
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final Map<String, String> columnTypeMap;

    /**
     * Default constructor setting column repository and creating default map for column types
     *
     * @param columnRepository column repository for service
     */
    @Autowired
    public ColumnService(final ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
        columnTypeMap = new HashMap<>();
        columnTypeMap.put("varchar", "varchar(255)");
    }

    /**
     * Method adding column to database
     *
     * @param apiKey     user api key
     * @param tableName  name of the table where new column will be added
     * @param columnName name of the new column
     * @param columnType type of the new column
     */
    public void addColumn(final String apiKey, final String tableName, final String columnName,
                          final String columnType) {
        String convertedColumnType = columnTypeMap.getOrDefault(columnType, columnType);
        columnRepository.addColumn(ServiceUtils.getTableName(apiKey, tableName), columnName, convertedColumnType);
    }

    /**
     * Method renaming existing column
     *
     * @param apiKey    user api key
     * @param tableName name of the table with column
     * @param oldName   old name of the column
     * @param newName   new name of the column
     */
    public void renameColumn(final String apiKey, final String tableName, final String oldName, final String newName) {
        columnRepository.renameColumn(ServiceUtils.getTableName(apiKey, tableName), oldName, newName);
    }

    /**
     * Method deleting existing column
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with column
     * @param columnName name of the deleting column
     */
    public void deleteColumn(final String apiKey, final String tableName, final String columnName) {
        columnRepository.deleteColumn(ServiceUtils.getTableName(apiKey, tableName), columnName);
    }

    /**
     * Method changing existing column type
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with column
     * @param columnName name of the column
     * @param newType    new ttype of the specified column
     */
    public void changeColumnType(final String apiKey, final String tableName, final String columnName,
                                 final String newType) {
        columnRepository.changeColumnType(ServiceUtils.getTableName(apiKey, tableName), columnName, newType);
    }

    /**
     * Method setting default value for column
     *
     * @param apiKey       user api key
     * @param tableName    name of the table with the column
     * @param columnName   name of the column
     * @param defaultValue new default value for specified column
     */
    public void changeColumnDefault(final String apiKey, final String tableName, final String columnName,
                                    final String defaultValue) {
        columnRepository.changeColumnDefault(ServiceUtils.getTableName(apiKey, tableName), columnName, defaultValue);
    }
}
