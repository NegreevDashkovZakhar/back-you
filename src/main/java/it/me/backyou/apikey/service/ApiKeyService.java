package it.me.backyou.apikey.service;

import it.me.backyou.apikey.ApiKey;
import it.me.backyou.apikey.repository.ApiKeyRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class
 * Works with api keys table
 */
@Service
public class ApiKeyService implements IApiKeyService {
    private static final Log LOG = LogFactory.getLog(ApiKeyService.class);
    private final ApiKeyRepository apiKeyRepository;

    /**
     * Default service constructor setting api key repository
     *
     * @param apiKeyRepository repository that will be used in all service methods
     */
    @Autowired
    public ApiKeyService(final ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
        LOG.info("Created with apiKeyRepository:" + apiKeyRepository);
    }

    @Override
    public void removeApiKey(final String apiKey) {
        ApiKey apiKeyInstance = apiKeyRepository.getApiKeyByValue(apiKey);
        LOG.info("removing api key:" + apiKey);
        if (apiKeyInstance != null) {
            apiKeyRepository.delete(apiKeyInstance);
            LOG.info("delete api key:" + apiKey);
        }
        LOG.info("ended removing api key:" + apiKey);
    }

    @Override
    public boolean hasApiKey(final String apiKey) {
        LOG.info("checking api key to existence:" + apiKey);
        return apiKeyRepository.hasApiKey(apiKey) == 1;
    }
}
