package it.me.backyou.service;

import it.me.backyou.controller.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ServiceUtilsTest {
    @Test
    public static void testNormalStringsGetTableName() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String actual = ServiceUtils.getTableName(apiKey, tableName);
        String expected = "testapikey__testtablename";
        assertEquals(expected, actual);
    }

    @Test
    public static void testEmptyStringGetTableName() {
        String apiKey = "testapikey";
        String tableName = "";
        try {
            String actual = ServiceUtils.getTableName(apiKey, tableName);
            fail();
        } catch (InvalidArgumentException ignored) {
        }
    }
}