package it.me.backyou.storage.service.table;

import it.me.backyou.apikey.exception.InvalidApiKeyException;
import it.me.backyou.apikey.service.IApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Service proxy class for api key verification for table service
 * Based on table service
 *
 * @see TableService
 */
@Service
@Primary
public class TableServiceProxy implements ITableService {
    private final ITableService actualService;
    private final IApiKeyService apiKeyService;

    /**
     * Default constructor initializing table service for proxying and api key service
     *
     * @param actualService table service that will do all the work
     * @param apiKeyService api key service for verifying api keys
     */
    @Autowired
    public TableServiceProxy(ITableService actualService, IApiKeyService apiKeyService) {
        this.actualService = actualService;
        this.apiKeyService = apiKeyService;
    }

    @Override
    public void createTable(String apiKey, String tableName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.createTable(apiKey, tableName);
    }

    @Override
    public void dropTable(String apiKey, String tableName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.dropTable(apiKey, tableName);
    }

    @Override
    public void renameTable(String apiKey, String oldName, String newName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.renameTable(apiKey, oldName, newName);
    }

    @Override
    public Object getTableHeaderData(String apiKey, String tableName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return actualService.getTableHeaderData(apiKey, tableName);
    }
}
