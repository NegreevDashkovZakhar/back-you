package it.me.backyou.storage.service.entry;

import it.me.backyou.apikey.exception.InvalidApiKeyException;
import it.me.backyou.apikey.service.IApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Service proxy class for api key verification for entry service
 * Based on entry service
 *
 * @see EntryService
 */
@Service
@Primary
public class EntryServiceProxy implements IEntryService {
    private final IEntryService actualService;
    private final IApiKeyService apiKeyService;

    /**
     * Default constructor initializing used api key service and actual service class
     *
     * @param actualService service instance that will do main work
     * @param apiKeyService api key service to verify keys
     */
    @Autowired
    public EntryServiceProxy(final IEntryService actualService, final IApiKeyService apiKeyService) {
        this.actualService = actualService;
        this.apiKeyService = apiKeyService;
    }

    @Override
    public Object getAllEntries(final String apiKey, final String tableName) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return actualService.getAllEntries(apiKey, tableName);
    }

    @Override
    public void addEntry(final String apiKey, final String tableName, final String[] columns, final String[] values) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.addEntry(apiKey, tableName, columns, values);
    }

    @Override
    public void deleteEntries(final String apiKey, final String tableName, final String columnName,
                              final String value) {
        if (!apiKeyService.hasApiKey(apiKey)) {
            throw new InvalidApiKeyException();
        }
        actualService.deleteEntries(apiKey, tableName, columnName, value);
    }
}
