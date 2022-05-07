package it.me.backyou.apikey.repository;

import it.me.backyou.apikey.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for working with api keys table
 */
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    /**
     * Method getting Api key instance from given UUID value
     *
     * @param apiKeyValue UUID value for api key
     * @return api key instance with specified value
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM api_key WHERE api_key.api_key = ?1 ;")
    ApiKey getApiKeyByValue(String apiKeyValue);

    /**
     * Method checking whether table holds given api key
     *
     * @param apiKey specified api key
     * @return 1 if table has entry with specified api key, 0 otherwise
     */
    @Query(nativeQuery = true,
            value = "SELECT COUNT(1) FROM api_key WHERE api_key.api_key = ?1 ;")
    int hasApiKey(String apiKey);
}