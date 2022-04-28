package it.me.backyou.user.response;

import it.me.backyou.user.User;
import it.me.backyou.apikey.ApiKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing response with list of api keys
 */
public class ApiKeysResponse {
    private final List<String> apiKeys;

    /**
     * Constructor initializing api keys list from given user
     *
     * @param user user from whom api keys will be taken
     */
    public ApiKeysResponse(final User user) {
        Set<ApiKey> userApiKeys = user.getApiKeys();
        apiKeys = new ArrayList<>();
        for (ApiKey apiKey : userApiKeys) {
            apiKeys.add(apiKey.getApiKey());
        }
    }

    /**
     * Constructor initializing api keys list from given api list
     *
     * @param apiKeySet api keys set that will be transformed to list
     */
    public ApiKeysResponse(final Set<ApiKey> apiKeySet) {
        apiKeys = new ArrayList<>();
        for (ApiKey apiKey : apiKeySet) {
            apiKeys.add(apiKey.getApiKey());
        }
    }

    public List<String> getApiKeys() {
        return apiKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiKeysResponse that = (ApiKeysResponse) o;
        return apiKeys.equals(that.apiKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKeys);
    }
}
