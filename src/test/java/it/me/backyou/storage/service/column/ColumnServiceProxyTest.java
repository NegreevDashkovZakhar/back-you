package it.me.backyou.storage.service.column;

import it.me.backyou.apikey.service.IApiKeyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;

class ColumnServiceProxyTest {
    private static ColumnServiceProxy columnServiceProxy;
    private static IColumnService columnService;
    private static IApiKeyService apiKeyService;

    @BeforeEach
    public void setUp() {
        columnService = Mockito.mock(IColumnService.class);
        apiKeyService = Mockito.mock(IApiKeyService.class);
        columnServiceProxy = new ColumnServiceProxy(columnService, apiKeyService);
    }

    @Test
    public void testAddColumn() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String columnType = "int";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        columnServiceProxy.addColumn(apiKey, tableName, columnName, columnType);
        Mockito.verify(columnService, Mockito.times(1))
                .addColumn(apiKey, tableName, columnName, columnType);
    }

    @Test
    public void testAddColumnIncorrectApiKey() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String columnType = "int";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            columnServiceProxy.addColumn(apiKey, tableName, columnName, columnType);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(columnService, Mockito.times(0))
                .addColumn(apiKey, tableName, columnName, columnType);
    }

    @Test
    public void testRenameColumn() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String newName = "col323";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        columnServiceProxy.renameColumn(apiKey, tableName, columnName, newName);
        Mockito.verify(columnService, Mockito.times(1))
                .renameColumn(apiKey, tableName, columnName, newName);
    }

    @Test
    public void testRenameColumnIncorrectApiKey() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String newName = "col2342";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            columnServiceProxy.renameColumn(apiKey, tableName, columnName, newName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(columnService, Mockito.times(0))
                .renameColumn(apiKey, tableName, columnName, newName);
    }

    @Test
    public void testDeleteColumn() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        columnServiceProxy.deleteColumn(apiKey, tableName, columnName);
        Mockito.verify(columnService, Mockito.times(1))
                .deleteColumn(apiKey, tableName, columnName);
    }

    @Test
    public void testDeleteColumnIncorrectApiKey() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            columnServiceProxy.deleteColumn(apiKey, tableName, columnName);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(columnService, Mockito.times(0))
                .deleteColumn(apiKey, tableName, columnName);
    }

    @Test
    public void testChangeColumnType() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String newColumnType = "int";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        columnServiceProxy.changeColumnType(apiKey, tableName, columnName, newColumnType);
        Mockito.verify(columnService, Mockito.times(1))
                .changeColumnType(apiKey, tableName, columnName, newColumnType);
    }

    @Test
    public void testChangeColumnTypeIncorrectApiKey() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String newColumnType = "int";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            columnServiceProxy.changeColumnType(apiKey, tableName, columnName, newColumnType);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(columnService, Mockito.times(0))
                .changeColumnType(apiKey, tableName, columnName, newColumnType);
    }

    @Test
    public void testChangeColumnDefault() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String defaultValue = "apple";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(true);
        columnServiceProxy.changeColumnDefault(apiKey, tableName, columnName, defaultValue);
        Mockito.verify(columnService, Mockito.times(1))
                .changeColumnDefault(apiKey, tableName, columnName, defaultValue);
    }

    @Test
    public void testChangeColumnDefaultIncorrectApiKey() {
        String apiKey = "sdjfns";
        String tableName = "table_1";
        String columnName = "col1";
        String defaultValue = "apple";
        Mockito.when(apiKeyService.hasApiKey(apiKey)).thenReturn(false);
        try {
            columnServiceProxy.changeColumnDefault(apiKey, tableName, columnName, defaultValue);
            fail();
        } catch (Exception ignored) {

        }
        Mockito.verify(columnService, Mockito.times(0))
                .changeColumnDefault(apiKey, tableName, columnName, defaultValue);
    }
}