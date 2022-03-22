package it.me.backyou.service;

import it.me.backyou.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final Map<String, String> columnTypeMap;

    @Autowired
    public ColumnService(final ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
        columnTypeMap = new HashMap<>();
        columnTypeMap.put("varchar", "varchar(255)");
    }

    public void addColumn(final String apiKey, final String tableName, final String columnName,
                          final String columnType) {
        String convertedColumnType = columnTypeMap.getOrDefault(columnType, columnType);
        columnRepository.addColumn(ServiceUtils.getTableName(apiKey, tableName), columnName, convertedColumnType);
    }

    public void renameColumn(final String apiKey, final String tableName, final String oldName, final String newName) {
        columnRepository.renameColumn(ServiceUtils.getTableName(apiKey, tableName), oldName, newName);
    }

    public void deleteColumn(final String apiKey, final String tableName, final String columnName) {
        columnRepository.deleteColumn(ServiceUtils.getTableName(apiKey, tableName), columnName);
    }

    public void changeColumnType(final String apiKey, final String tableName, final String columnName,
                                 final String newType) {
        columnRepository.changeColumnType(ServiceUtils.getTableName(apiKey, tableName), columnName, newType);
    }

    public void changeColumnDefault(final String apiKey, final String tableName, final String columnName,
                                    final String defaultValue) {
        columnRepository.changeColumnDefault(ServiceUtils.getTableName(apiKey, tableName), columnName, defaultValue);
    }
}
