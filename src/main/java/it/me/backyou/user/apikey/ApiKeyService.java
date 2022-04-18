package it.me.backyou.user.apikey;

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
        apiKeyRepository.deleteById(apiKey);
    }

    @Override
    public boolean hasApiKey(final String apiKey) {
        return apiKeyRepository.existsById(apiKey);
    }
}