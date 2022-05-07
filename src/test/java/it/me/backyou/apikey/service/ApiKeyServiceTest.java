package it.me.backyou.apikey.service;

import it.me.backyou.apikey.ApiKey;
import it.me.backyou.apikey.repository.ApiKeyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiKeyServiceTest {
    private static ApiKeyService apiKeyService;
    private static ApiKeyRepository apiKeyRepository;

    @BeforeAll
    static void setUp() {
        apiKeyRepository = Mockito.mock(ApiKeyRepository.class);
        apiKeyService = new ApiKeyService(apiKeyRepository);
    }

    @Test
    public void testHasApiKeyTrue() {
        String apiKey = "blabla";
        Mockito.when(apiKeyRepository.hasApiKey(apiKey)).thenReturn(1);
        boolean expected = true;
        boolean actual = apiKeyService.hasApiKey(apiKey);
        assertEquals(expected, actual);
    }

    @Test
    public void testHasApiKeyFalse() {
        String apiKey = "blabla";
        Mockito.when(apiKeyRepository.hasApiKey(apiKey)).thenReturn(0);
        boolean expected = false;
        boolean actual = apiKeyService.hasApiKey(apiKey);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteExistingApiKey() {
        ApiKey apiKey = new ApiKey();
        String apiKeyString = "blabla";
        Mockito.when(apiKeyRepository.getApiKeyByValue(apiKeyString)).thenReturn(apiKey);
        apiKeyService.removeApiKey(apiKeyString);
        Mockito.verify(apiKeyRepository, Mockito.times(1)).delete(apiKey);
    }

    @Test
    public void testDeleteNotExistingApiKey() {
        String apiKeyString = "blabla";
        Mockito.when(apiKeyRepository.getApiKeyByValue(apiKeyString)).thenReturn(null);
        apiKeyService.removeApiKey(apiKeyString);
        Mockito.verify(apiKeyRepository, Mockito.times(0)).delete(Mockito.any(ApiKey.class));
    }
}