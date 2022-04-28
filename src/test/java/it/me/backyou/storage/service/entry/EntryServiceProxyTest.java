package it.me.backyou.storage.service.entry;

import it.me.backyou.apikey.service.IApiKeyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EntryServiceProxyTest {
    private static EntryServiceProxy entryServiceProxy;
    private static IEntryService entryService;
    private static IApiKeyService apiKeyService;

    @BeforeEach
    public void setUp() {
        entryService = Mockito.mock(IEntryService.class);
        apiKeyService = Mockito.mock(IApiKeyService.class);
        entryServiceProxy = new EntryServiceProxy(entryService, apiKeyService);
    }

    @Test
    public void testGetAllEntries() {
        String apiKey = "aldkfjasdog";
        String tableName = "table_1";
        Object expected = new Object();
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        Mockito.when(entryService.getAllEntries(apiKey, tableName)).thenReturn(expected);
        Object actual = entryServiceProxy.getAllEntries(apiKey, tableName);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllEntriesInvalidApiKey() {
        String apiKey = "aldkfjasdog";
        String tableName = "table_1";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            entryServiceProxy.getAllEntries(apiKey, tableName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(entryService, Mockito.times(0)).getAllEntries(apiKey, tableName);
    }

    @Test
    public void testAddEntry() {
        String apiKey = "slkdfgnlsdg";
        String tableName = "table1";
        String[] columns = new String[4];
        String[] values = new String[4];
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        entryServiceProxy.addEntry(apiKey, tableName, columns, values);
        Mockito.verify(entryService, Mockito.times(1))
                .addEntry(apiKey, tableName, columns, values);
    }

    @Test
    public void testAddEntryInvalidApiKey() {
        String apiKey = "slkdfgnlsdg";
        String tableName = "table1";
        String[] columns = new String[4];
        String[] values = new String[4];
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            entryServiceProxy.addEntry(apiKey, tableName, columns, values);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(entryService, Mockito.times(0))
                .addEntry(apiKey, tableName, columns, values);
    }

    @Test
    public void testDeleteEntries() {
        String apiKey = "slkdfgnlsdg";
        String tableName = "table1";
        String column = "food";
        String value = "apple";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        entryServiceProxy.deleteEntries(apiKey, tableName, column, value);
        Mockito.verify(entryService, Mockito.times(1))
                .deleteEntries(apiKey, tableName, column, value);
    }

    @Test
    public void testDeleteEntriesInvalidApiKey() {
        String apiKey = "slkdfgnlsdg";
        String tableName = "table1";
        String column = "food";
        String value = "apple";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            entryServiceProxy.deleteEntries(apiKey, tableName, column, value);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(entryService, Mockito.times(0))
                .deleteEntries(apiKey, tableName, column, value);
    }
}