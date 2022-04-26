package it.me.backyou.apikey;

import java.util.Random;

/**
 * Utils class for generating api keys
 */
public class ApiKeyUtils {
    private static final Random random = new Random();
    private static final int API_KEY_LENGTH = 20;

    /**
     * Method generating api keys
     *
     * @return generated api key of length 20 and containing only lower case letters
     */
    public static String generateApiKey() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < API_KEY_LENGTH; i++) {
            stringBuilder.append((char) ('a' + random.nextInt(20)));
        }
        return stringBuilder.toString();
    }
}
