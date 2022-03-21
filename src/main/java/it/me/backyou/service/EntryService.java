package it.me.backyou.service;

import it.me.backyou.query.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {
    private final QueryExecutor queryExecutor;

    @Autowired
    public EntryService(final QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public Object getAllEntries(final String apiKey, final String tableName) {
        return queryExecutor.getAllEntries(ServiceUtils.getTableName(apiKey, tableName));
    }

    public void addEntry(final String apiKey, final String tableName, final String[] columns, final String[] values) {
        queryExecutor.addEntry(ServiceUtils.getTableName(apiKey, tableName), columns, values);
    }
}
