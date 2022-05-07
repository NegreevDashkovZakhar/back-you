package it.me.backyou.storage.controller;

import it.me.backyou.storage.controller.TableController;
import it.me.backyou.storage.service.table.ITableService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableControllerTest {
    private static TableController tableController;
    private static ITableService tableService;

    @BeforeAll
    public static void setUp() {
        tableService = Mockito.mock(ITableService.class);
        tableController = new TableController(tableService);
    }

    @Test
    public void testCreateTable() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        tableController.createTable(apiKey, tableName);
        Mockito.verify(tableService, Mockito.times(1)).createTable(apiKey, tableName);
    }

    @Test
    public void testDropTable() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        tableController.dropTable(apiKey, tableName);
        Mockito.verify(tableService, Mockito.times(1)).dropTable(apiKey, tableName);
    }

    @Test
    public void testRenameable() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String newTableName = "newtablename";
        tableController.renameTable(apiKey, tableName, newTableName);
        Mockito.verify(tableService, Mockito.times(1)).renameTable(apiKey, tableName, newTableName);
    }

    @Test
    public void testGetHeaderData() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        Object expected = new Object();
        Mockito.when(tableService.getTableHeaderData(apiKey, tableName)).thenReturn(expected);
        Object actual = tableController.getTableHeaderData(apiKey, tableName);
        Mockito.verify(tableService, Mockito.times(1)).getTableHeaderData(apiKey, tableName);
        assertEquals(expected, actual);
    }
}