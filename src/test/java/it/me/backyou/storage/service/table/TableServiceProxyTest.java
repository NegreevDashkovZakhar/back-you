package it.me.backyou.storage.service.table;

import it.me.backyou.apikey.service.IApiKeyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TableServiceProxyTest {
    private static TableServiceProxy tableServiceProxy;
    private static ITableService tableService;
    private static IApiKeyService apiKeyService;

    @BeforeEach
    public void setUp() {
        tableService = Mockito.mock(ITableService.class);
        apiKeyService = Mockito.mock(IApiKeyService.class);
        tableServiceProxy = new TableServiceProxy(tableService, apiKeyService);
    }

    @Test
    public void testCreateTable() {
        String apiKey = "aslfjalf";
        String tableName = "table_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        tableServiceProxy.createTable(apiKey, tableName);
        Mockito.verify(tableService, Mockito.times(1)).createTable(apiKey, tableName);
    }

    @Test
    public void testCreateTableInvalidApiKey() {
        String apiKey = "dsfnlsd";
        String tableName = "table_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            tableServiceProxy.createTable(apiKey, tableName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(tableService, Mockito.times(0)).createTable(apiKey, tableName);
    }

    @Test
    public void testDropTable() {
        String apiKey = "aslfjalf";
        String tableName = "table_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        tableServiceProxy.dropTable(apiKey, tableName);
        Mockito.verify(tableService, Mockito.times(1)).dropTable(apiKey, tableName);
    }

    @Test
    public void testDropTableInvalidApiKey() {
        String apiKey = "dsfnlsd";
        String tableName = "table_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            tableServiceProxy.dropTable(apiKey, tableName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(tableService, Mockito.times(0)).dropTable(apiKey, tableName);
    }

    @Test
    public void testRenameTable() {
        String apiKey = "aslfjalf";
        String tableName = "table_food";
        String newName = "table_vegetarian_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        tableServiceProxy.renameTable(apiKey, tableName, newName);
        Mockito.verify(tableService, Mockito.times(1)).renameTable(apiKey, tableName, newName);
    }

    @Test
    public void testRenameTableInvalidApiKey() {
        String apiKey = "dsfnlsd";
        String tableName = "table_food";
        String newName = "table_vegetarian_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            tableServiceProxy.renameTable(apiKey, tableName, newName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(tableService, Mockito.times(0)).renameTable(apiKey, tableName, newName);
    }

    @Test
    public void testGetTableHeaderData() {
        String apiKey = "dsfnlsd";
        String tableName = "table_food";
        Object expected = new Object();
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        Mockito.when(tableService.getTableHeaderData(apiKey, tableName)).thenReturn(expected);
        Object actual = tableServiceProxy.getTableHeaderData(apiKey, tableName);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTableHeaderDataInvalidApiKey() {
        String apiKey = "dsfnlsd";
        String tableName = "table_food";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            tableServiceProxy.getTableHeaderData(apiKey, tableName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(tableService, Mockito.times(0)).getTableHeaderData(apiKey, tableName);
    }
}