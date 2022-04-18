package it.me.backyou.apikey;

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
            value = "SELECT * FROM api_key WHERE apiKey = ?1 ;")
    ApiKey getApiKeyByValue(String apiKeyValue);
}