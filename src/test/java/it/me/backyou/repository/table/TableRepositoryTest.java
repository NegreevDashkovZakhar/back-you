package it.me.backyou.repository.table;

import it.me.backyou.storage.repository.query.QueryExecutor;
import it.me.backyou.storage.repository.table.TableRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableRepositoryTest {
    private static TableRepository tableRepository;
    private static QueryExecutor queryExecutor;

    @BeforeAll
    public static void setUp() {
        queryExecutor = Mockito.mock(QueryExecutor.class);
        tableRepository = new TableRepository(queryExecutor);
    }

    @Test
    public void testCreateTable() {
        String tableName = "table_name__test";
        String expectedSQL = "CREATE TABLE table_name__test(id serial PRIMARY KEY);";
        tableRepository.createTable(tableName);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testDropTable() {
        String tableName = "table_name__test";
        String expectedSQL = "DROP TABLE table_name__test;";
        tableRepository.dropTable(tableName);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testRenameTable() {
        String tableName = "table_name__test";
        String newTableName = "table_name__new_name";
        String expectedSQL = "ALTER TABLE table_name__test RENAME TO table_name__new_name;";
        tableRepository.renameTable(tableName, newTableName);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testGetTableHeaderData() {
        String tableName = "table_name__test";
        String expectedSQL = "SELECT column_name, data_type, column_default FROM information_schema.columns WHERE " +
                "table_name = 'table_name__test';";
        Object expected = new Object();
        Mockito.when(queryExecutor.executeToObject(expectedSQL)).thenReturn(expected);
        Object actual = tableRepository.getTableHeaderData(tableName);
        assertEquals(expected, actual);
    }
}