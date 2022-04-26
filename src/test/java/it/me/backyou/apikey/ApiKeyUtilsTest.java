package it.me.backyou.apikey;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiKeyUtilsTest {

    @Test
    public void testGenerateApiKey() {
        String apiKey = ApiKeyUtils.generateApiKey();
        int expectedLength = 20;
        boolean expectedNoNumbers = true;
        assertEquals(expectedLength, apiKey.length());
        assertEquals(expectedNoNumbers, apiKey.matches("\\D*"));
    }
}