package it.me.backyou.storage.service.column;

import it.me.backyou.storage.repository.column.IColumnRepository;
import it.me.backyou.storage.service.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class
 * Holds logic for executing queries for columns
 */
@Service
public class ColumnService implements IColumnService {
    private final IColumnRepository columnRepository;
    private final Map<String, String> columnTypeMap;

    /**
     * Default constructor setting column repository and creating default map for column types
     *
     * @param columnRepository column repository for service
     */
    @Autowired
    public ColumnService(final IColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
        columnTypeMap = new HashMap<>();
        columnTypeMap.put("varchar", "varchar(255)");
    }

    @Override
    public void addColumn(final String apiKey, final String tableName, final String columnName,
                          final String columnType) {
        String convertedColumnType = columnTypeMap.getOrDefault(columnType, columnType);
        columnRepository.addColumn(ServiceUtils.getTableName(apiKey, tableName), columnName, convertedColumnType);
    }

    @Override
    public void renameColumn(final String apiKey, final String tableName, final String oldName, final String newName) {
        columnRepository.renameColumn(ServiceUtils.getTableName(apiKey, tableName), oldName, newName);
    }

    @Override
    public void deleteColumn(final String apiKey, final String tableName, final String columnName) {
        columnRepository.deleteColumn(ServiceUtils.getTableName(apiKey, tableName), columnName);
    }

    @Override
    public void changeColumnType(final String apiKey, final String tableName, final String columnName,
                                 final String newType) {
        String convertedColumnType = columnTypeMap.getOrDefault(newType, newType);
        columnRepository.changeColumnType(ServiceUtils.getTableName(apiKey, tableName), columnName, convertedColumnType);
    }

    @Override
    public void changeColumnDefault(final String apiKey, final String tableName, final String columnName,
                                    final String defaultValue) {
        columnRepository.changeColumnDefault(ServiceUtils.getTableName(apiKey, tableName), columnName, defaultValue);
    }
}
