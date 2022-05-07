package it.me.backyou.storage.controller;

import it.me.backyou.storage.controller.ColumnController;
import it.me.backyou.storage.service.column.IColumnService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ColumnControllerTest {
    private static ColumnController columnController;
    private static IColumnService columnService;

    @BeforeAll
    public static void setUp() {
        columnService = Mockito.mock(IColumnService.class);
        columnController = new ColumnController(columnService);
    }

    @Test
    public void testAddColumn() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "columnname";
        String columnType = "varchar";

        columnController.addColumn(apiKey, tableName, columnName, columnType);
        Mockito.verify(columnService, Mockito.times(1))
                .addColumn(apiKey, tableName, columnName, columnType);
    }

    @Test
    public void testRenameColumn() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "columnname";
        String newColumnName = "column_2";

        columnController.renameColumn(apiKey, tableName, columnName, newColumnName);
        Mockito.verify(columnService, Mockito.times(1))
                .renameColumn(apiKey, tableName, columnName, newColumnName);
    }

    @Test
    public void testDeleteColumn() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "columnname";

        columnController.deleteColumn(apiKey, tableName, columnName);
        Mockito.verify(columnService, Mockito.times(1))
                .deleteColumn(apiKey, tableName, columnName);
    }

    @Test
    public void testChangeColumnType() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "columnname";
        String newColumnType = "varchar";

        columnController.changeColumnType(apiKey, tableName, columnName, newColumnType);
        Mockito.verify(columnService, Mockito.times(1))
                .changeColumnType(apiKey, tableName, columnName, newColumnType);
    }

    @Test
    public void testChangeColumnDefaultValue() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "columnname";
        String defaultValue = "my_default";

        columnController.changeColumnDefault(apiKey, tableName, columnName, defaultValue);
        Mockito.verify(columnService, Mockito.times(1))
                .changeColumnDefault(apiKey, tableName, columnName, defaultValue);
    }

}