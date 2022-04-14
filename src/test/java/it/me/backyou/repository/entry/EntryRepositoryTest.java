package it.me.backyou.repository.entry;

import it.me.backyou.repository.query.QueryExecutor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryTest {
    private static EntryRepository entryRepository;
    private static QueryExecutor queryExecutor;

    @BeforeAll
    public static void setUp() {
        queryExecutor = Mockito.mock(QueryExecutor.class);
        entryRepository = new EntryRepository(queryExecutor);
    }

    @Test
    public void testGetAllEntries() {
        String tableName = "wow__table_w";
        String expectedSQL = "SELECT * FROM wow__table_w;";
        Object expected = new Object();
        Mockito.when(queryExecutor.executeToObject(expectedSQL)).thenReturn(expected);
        Object actual = entryRepository.getAllEntries(tableName);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddEntry() {
        String tableName = "wow__table_w";
        String[] columns = new String[]{"col_1", "my_col"};
        String[] values = new String[]{"val_1", "west"};
        String expectedSQL = "INSERT INTO wow__table_w(col_1, my_col) VALUES ('val_1', 'west');";
        entryRepository.addEntry(tableName, columns, values);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testAddEntryOneColumn() {
        String tableName = "wow__table_w";
        String[] columns = new String[]{"col_1"};
        String[] values = new String[]{"val_1"};
        String expectedSQL = "INSERT INTO wow__table_w(col_1) VALUES ('val_1');";
        entryRepository.addEntry(tableName, columns, values);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testRemoveEntries() {
        String tableName = "wow__table_w";
        String columnName = "col_1";
        String value = "check_val";
        String expectedSQL = "DELETE FROM wow__table_w WHERE col_1='check_val';";
        entryRepository.removeEntries(tableName, columnName, value);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }
}