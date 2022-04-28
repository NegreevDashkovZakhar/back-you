package it.me.backyou.user.controller;

import it.me.backyou.apikey.ApiKey;
import it.me.backyou.user.exception.UserNotFoundException;
import it.me.backyou.user.response.ApiKeysResponse;
import it.me.backyou.user.service.IUserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UserControllerTest {
    private static UserController userController;
    private static IUserService userService;

    @BeforeAll
    static void setUp() {
        userService = Mockito.mock(IUserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void testRegisterUser() {
        long expected = 34L;
        String email = "expample@mail.ru";
        String password = "qwerty";
        Mockito.when(userService.registerUser(email, password)).thenReturn(expected);
        Long actual = userController.registerUser(email, password);
        Mockito.verify(userService, Mockito.times(1)).registerUser(email, password);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserApiKeys() {
        long userId = 23L;
        Set<ApiKey> apiKeySet = new HashSet<>();
        ApiKeysResponse expected = new ApiKeysResponse(apiKeySet);
        Mockito.when(userService.getUserApiKeys(userId)).thenReturn(apiKeySet);
        ApiKeysResponse actual = userController.getUserApiKeys(userId);
        assertEquals(expected, actual);
        Mockito.verify(userService, Mockito.times(1)).getUserApiKeys(userId);
    }

    @Test
    public void testAddApiKey() {
        long userId = 435L;
        String expected = "api_key_elephant";
        Mockito.when(userService.addNewApiKey(userId)).thenReturn(expected);
        String actual = userController.addApiKey(userId);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserId() {
        long expected = 23423L;
        String email = "example@mail.ru";
        String password = "qwerty";
        Mockito.when(userService.getUserId(email, password)).thenReturn(expected);
        long actual = userController.getUserId(email, password);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteApiKey() {
        String apiKey = "sdfjsdlkfj";
        String email = "example@mail.ru";
        String password = "qwerty";
        long userId = 21L;
        Mockito.when(userService.getUserId(email, password)).thenReturn(userId);
        userController.deleteApiKey(email, password, apiKey);
        Mockito.verify(userService, Mockito.times(1)).deleteUserApiKey(userId, apiKey);
    }

    @Test
    public void testDeleteNotExistingUserApiKey() {
        String apiKey = "sdfjsdlkfj";
        String email = "example@mail.ru";
        String password = "qwerty";
        Mockito.when(userService.getUserId(email, password)).thenThrow(new UserNotFoundException());
        try {
            userController.deleteApiKey(email, password, apiKey);
            fail();
        } catch (Exception ignored) {

        }
    }
}