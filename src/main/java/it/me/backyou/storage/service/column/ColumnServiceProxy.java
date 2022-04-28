package it.me.backyou.storage.service.column;

import it.me.backyou.apikey.exception.InvalidApiKeyException;
import it.me.backyou.apikey.service.IApiKeyService;
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
    public ColumnServiceProxy(final IColumnService actualService, final IApiKeyService apiKeyService) {
        this.actualService = actualService;
        this.apiKeyService = apiKeyService;
    }

    @Override
    public void addColumn(final String apiKey, final String tableName, final String columnName,
                          final String columnType) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.addColumn(apiKey, tableName, columnName, columnType);
    }

    @Override
    public void renameColumn(final String apiKey, final String tableName, final String oldName, final String newName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.renameColumn(apiKey, tableName, oldName, newName);
    }

    @Override
    public void deleteColumn(final String apiKey, final String tableName, final String columnName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.deleteColumn(apiKey, tableName, columnName);
    }

    @Override
    public void changeColumnType(final String apiKey, final String tableName, final String columnName,
                                 final String newType) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.changeColumnType(apiKey, tableName, columnName, newType);
    }

    @Override
    public void changeColumnDefault(final String apiKey, final String tableName, final String columnName,
                                    final String defaultValue) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.changeColumnDefault(apiKey, tableName, columnName, defaultValue);
    }
}
