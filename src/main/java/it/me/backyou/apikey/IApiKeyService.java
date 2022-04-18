package it.me.backyou.apikey;

/**
 * Service interface for working with api keys
 */
public interface IApiKeyService {
    /**
     * Method removing api key from table
     *
     * @param apiKey api key to be removed
     */
    void removeApiKey(String apiKey);

    /**
     * Method for checking whether specified api key is registered
     *
     * @param apiKey checked api key
     * @return true if given api key exists and is active, false otherwise
     */
    boolean hasApiKey(String apiKey);
}
