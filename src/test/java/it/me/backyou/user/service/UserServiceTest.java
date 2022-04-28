package it.me.backyou.user.service;

import it.me.backyou.apikey.ApiKey;
import it.me.backyou.apikey.repository.ApiKeyRepository;
import it.me.backyou.user.User;
import it.me.backyou.user.exception.UserNotFoundException;
import it.me.backyou.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UserServiceTest {
    private static UserService userService;
    private static UserRepository userRepository;
    private static ApiKeyRepository apiKeyRepository;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        apiKeyRepository = Mockito.mock(ApiKeyRepository.class);
        userService = new UserService(userRepository, apiKeyRepository);
    }

    //TODO add register user test

    @Test
    public void testGetUserApiKeys() {
        long userId = 12L;
        User user = new User();
        Set<ApiKey> apiKeys = new HashSet<>();
        user.setApiKeys(apiKeys);

        Mockito.when(userRepository.getById(userId)).thenReturn(user);
        Set<ApiKey> actual = userService.getUserApiKeys(userId);
        assertEquals(apiKeys, actual);
    }

    @Test
    public void testGetNotExistingUserApiKeys() {
        long userId = 12L;

        Mockito.when(userRepository.getById(userId)).thenThrow(new UserNotFoundException());
        try {
            userService.getUserApiKeys(userId);
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testAddNewApiKey() {
        long userId = 12L;
        userService.addNewApiKey(userId);
        Mockito.verify(userRepository, Mockito.times(1)).getById(userId);
        Mockito.verify(apiKeyRepository, Mockito.times(1)).save(Mockito.any(ApiKey.class));
    }

    @Test
    public void testAddNewApiKeyNotExistingUser() {
        long userId = 12L;
        Mockito.when(userRepository.getById(userId)).thenThrow(new UserNotFoundException());
        try {
            userService.addNewApiKey(userId);
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testGetUserId() {
        String email = "example@gmail.com";
        String password = "dlsfms";
        long expected = 12L;
        Mockito.when(userRepository.getUserId(email, password)).thenReturn(expected);
        long actual = userService.getUserId(email, password);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNotExistingUserId() {
        String email = "example@gmail.com";
        String password = "dlsfms";
        Mockito.when(userRepository.getUserId(email, password)).thenThrow(new UserNotFoundException());
        try {
            userService.getUserId(email, password);
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testDeleteUserApiKey() {
        long userId = 234L;
        String apiKey = "sdjnsldsdfsd";
        ApiKey apiKeyInstance = new ApiKey(apiKey);
        User user = new User();
        user.setUserId(userId);
        apiKeyInstance.setUser(user);
        Mockito.when(apiKeyRepository.getApiKeyByValue(apiKey)).thenReturn(apiKeyInstance);
        userService.deleteUserApiKey(userId, apiKey);
        Mockito.verify(apiKeyRepository, Mockito.times(1)).delete(apiKeyInstance);
    }

    @Test
    public void testDeleteAnotherUserApiKey() {
        long userId = 234L;
        long anotherUserId = 23L;
        String apiKey = "sdjnsldsdfsd";
        ApiKey apiKeyInstance = new ApiKey(apiKey);
        User user = new User();
        user.setUserId(anotherUserId);
        apiKeyInstance.setUser(user);
        Mockito.when(apiKeyRepository.getApiKeyByValue(apiKey)).thenReturn(apiKeyInstance);
        try {
            userService.deleteUserApiKey(userId, apiKey);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(apiKeyRepository, Mockito.times(0)).delete(apiKeyInstance);
    }
}