package it.me.backyou.controller;

import it.me.backyou.storage.controller.EntryController;
import it.me.backyou.storage.controller.request.AddEntryRequest;
import it.me.backyou.storage.service.entry.IEntryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryControllerTest {
    private static EntryController entryController;
    private static IEntryService entryService;

    @BeforeAll
    public static void setUp() {
        entryService = Mockito.mock(IEntryService.class);
        entryController = new EntryController(entryService);
    }

    @Test
    public void testGetAllEntries() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        Object expected = new Object();
        Mockito.when(entryService.getAllEntries(apiKey, tableName)).thenReturn(expected);
        Object actual = entryController.getAllEntries(apiKey, tableName);
        Mockito.verify(entryService, Mockito.times(1)).getAllEntries(apiKey, tableName);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddEntry() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String[] columns = new String[]{"1", "2"};
        String[] values = new String[]{"qwe", "rty"};
        AddEntryRequest addEntryRequest = new AddEntryRequest(columns, values);

        entryController.addEntry(apiKey, tableName, addEntryRequest);
        Mockito.verify(entryService, Mockito.times(1)).addEntry(apiKey, tableName, columns, values);
    }

    @Test
    public void testDeleteEntries() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "column";
        String value = "val";
        entryController.deleteEntries(apiKey, tableName, columnName, value);
        Mockito.verify(entryService, Mockito.times(1)).deleteEntries(apiKey, tableName, columnName, value);
    }
}