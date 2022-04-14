package it.me.backyou.service.table;

import it.me.backyou.repository.table.ITableRepository;
import it.me.backyou.service.ServiceUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class TableServiceTest {
    private static TableService tableService;
    private static ITableRepository tableRepository;

    @BeforeAll
    public static void setUp() {
        tableRepository = Mockito.mock(ITableRepository.class);
        tableService = new TableService(tableRepository);
    }

    @Test
    public void testCreateTable() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        tableService.createTable(apiKey, tableName);
        Mockito.verify(tableRepository, Mockito.times(1)).createTable(expectedTableName);
    }

    @Test
    public void testDropTable() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        tableService.dropTable(apiKey, tableName);
        Mockito.verify(tableRepository, Mockito.times(1)).dropTable(expectedTableName);
    }

    @Test
    public void testRenameTable() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String newTableName = "new_table_name";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        String newExpectedTableName = ServiceUtils.getTableName(apiKey, newTableName);
        tableService.renameTable(apiKey, tableName, newTableName);
        Mockito.verify(tableRepository, Mockito.times(1))
                .renameTable(expectedTableName, newExpectedTableName);
    }

    @Test
    public void testGetTableHeaderData() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        tableService.getTableHeaderData(apiKey, tableName);
        Mockito.verify(tableRepository, Mockito.times(1)).getTableHeaderData(expectedTableName);
    }

}