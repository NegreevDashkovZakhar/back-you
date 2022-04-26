package it.me.backyou.storage.service.column;

import it.me.backyou.apikey.service.IApiKeyService;
import it.me.backyou.apikey.exception.InvalidApiKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Service proxy class for api key verification for column service
 * Based on column service
 *
 * @see ColumnService
 */
@Service
@Primary
public class ColumnServiceProxy implements IColumnService {
    private final IColumnService actualService;
    private final IApiKeyService apiKeyService;

    /**
     * Default constructor initializing used api key service and actual service class
     *
     * @param actualService service instance that will do main work
     * @param apiKeyService api key service to verify keys
     */
    @Autowired
    public ColumnServiceProxy(ColumnService actualService, IApiKeyService apiKeyService) {
        this.actualService = actualService;
        this.apiKeyService = apiKeyService;
    }

    @Override
    public void addColumn(String apiKey, String tableName, String columnName, String columnType) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.addColumn(apiKey, tableName, columnName, columnType);
    }

    @Override
    public void renameColumn(String apiKey, String tableName, String oldName, String newName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.renameColumn(apiKey, tableName, oldName, newName);
    }

    @Override
    public void deleteColumn(String apiKey, String tableName, String columnName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.deleteColumn(apiKey, tableName, columnName);
    }

    @Override
    public void changeColumnType(String apiKey, String tableName, String columnName, String newType) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.changeColumnType(apiKey, tableName, columnName, newType);
    }

    @Override
    public void changeColumnDefault(String apiKey, String tableName, String columnName, String defaultValue) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.changeColumnDefault(apiKey, tableName, columnName, defaultValue);
    }
}
