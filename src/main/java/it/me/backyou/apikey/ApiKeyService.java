package it.me.backyou.apikey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class
 * Works with api keys table
 */
@Service
public class ApiKeyService implements IApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    /**
     * Default service constructor setting api key repository
     *
     * @param apiKeyRepository repository that will be used in all service methods
     */
    @Autowired
    public ApiKeyService(final ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void removeApiKey(final String apiKey) {
        apiKeyRepository.delete(apiKeyRepository.getApiKeyByValue(apiKey));
    }

    @Override
    public boolean hasApiKey(final String apiKey) {
        return apiKeyRepository.hasApiKey(apiKey) == 1;
    }
}
