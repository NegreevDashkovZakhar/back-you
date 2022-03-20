package it.me.backyou.service;

import it.me.backyou.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ColumnService {
    private final QueryExecutor queryExecutor;
    private final Map<String, String> columnTypeMap;

    @Autowired
    public ColumnService(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
        columnTypeMap = new HashMap<>();
        columnTypeMap.put("varchar", "varchar(255)");
    }

    public void addColumn(final String apiKey, final String tableName, final String columnName,
                          final String columnType) {
        String convertedColumnType = columnTypeMap.getOrDefault(columnType, columnType);
        queryExecutor.addColumn(ServiceUtils.getTableName(apiKey, tableName), columnName, convertedColumnType);
    }

    public void renameColumn(final String apiKey, final String tableName, final String oldName, final String newName) {
        queryExecutor.renameColumn(ServiceUtils.getTableName(apiKey, tableName), oldName, newName);
    }

    public void deleteColumn(final String apiKey, final String tableName, final String columnName) {
        queryExecutor.deleteColumn(ServiceUtils.getTableName(apiKey, tableName), columnName);
    }

    public void changeColumnType(final String apiKey, final String tableName, final String columnName,
                                 final String newType) {
        queryExecutor.changeColumnType(ServiceUtils.getTableName(apiKey, tableName), columnName, newType);
    }

    public void changeColumnDefault(final String apiKey, final String tableName, final String columnName,
                                    final String defaultValue) {
        queryExecutor.changeColumnDefault(ServiceUtils.getTableName(apiKey, tableName), columnName, defaultValue);
    }
}
