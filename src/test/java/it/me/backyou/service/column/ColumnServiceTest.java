package it.me.backyou.service.column;

import it.me.backyou.repository.column.IColumnRepository;
import it.me.backyou.service.ServiceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ColumnServiceTest {
    private static ColumnService columnService;
    private static IColumnRepository columnRepository;

    @BeforeAll
    public static void setUp() {
        columnRepository = Mockito.mock(IColumnRepository.class);
        columnService = new ColumnService(columnRepository);
    }

    @Test
    public void testAddColumn() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String columnType = "int";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        columnService.addColumn(apiKey, tableName, columnName, columnType);
        Mockito.verify(columnRepository, Mockito.times(1))
                .addColumn(expectedTableName, columnName, columnType);
    }

    @Test
    public void testAddColumnShortenedType() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String columnType = "varchar";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        String expectedColumnType = "varchar(255)";
        columnService.addColumn(apiKey, tableName, columnName, columnType);
        Mockito.verify(columnRepository, Mockito.times(1))
                .addColumn(expectedTableName, columnName, expectedColumnType);
    }

    @Test
    public void testRenameColumn() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String newColumnName = "column_2";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        columnService.renameColumn(apiKey, tableName, columnName, newColumnName);
        Mockito.verify(columnRepository, Mockito.times(1))
                .renameColumn(expectedTableName, columnName, newColumnName);
    }

    @Test
    public void testDeleteColumn() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        columnService.deleteColumn(apiKey, tableName, columnName);
        Mockito.verify(columnRepository, Mockito.times(1))
                .deleteColumn(expectedTableName, columnName);
    }

    @Test
    public void testChangeColumnType() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String newColumnType = "int";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        columnService.changeColumnType(apiKey, tableName, columnName, newColumnType);
        Mockito.verify(columnRepository, Mockito.times(1))
                .changeColumnType(expectedTableName, columnName, newColumnType);
    }

    @Test
    public void testChangeColumnShortenedType() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String newColumnType = "varchar";
        String expectedColumnType = "varchar(255)";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        columnService.changeColumnType(apiKey, tableName, columnName, newColumnType);
        Mockito.verify(columnRepository, Mockito.times(1))
                .changeColumnType(expectedTableName, columnName, expectedColumnType);
    }

    @Test
    public void testChangeColumnDefault() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "my_col";
        String newColumnDefault = "default_val";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        columnService.changeColumnDefault(apiKey, tableName, columnName, newColumnDefault);
        Mockito.verify(columnRepository, Mockito.times(1))
                .changeColumnDefault(expectedTableName, columnName, newColumnDefault);
    }
}