package it.me.backyou.service;

import it.me.backyou.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {
    private final QueryExecutor queryExecutor;

    @Autowired
    public TableService(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public void createTable(final String apiKey, final String tableName) {
        queryExecutor.createTable(getTableName(apiKey, tableName));
    }

    public void dropTable(final String apiKey, final String tableName) {
        queryExecutor.dropTable(getTableName(apiKey, tableName));
    }

    private String getTableName(final String apiKey, final String tableName) {
        return apiKey + "__" + tableName;
    }
}
