package it.me.backyou.service.entry;

import it.me.backyou.storage.repository.entry.IEntryRepository;
import it.me.backyou.storage.service.ServiceUtils;
import it.me.backyou.storage.service.entry.EntryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryServiceTest {
    private static EntryService entryService;
    private static IEntryRepository entryRepository;

    @BeforeAll
    public static void setUp() {
        entryRepository = Mockito.mock(IEntryRepository.class);
        entryService = new EntryService(entryRepository);
    }

    @Test
    public void testGetAllEntries() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        Object expected = new Object();
        Mockito.when(entryRepository.getAllEntries(expectedTableName)).thenReturn(expected);
        Object actual = entryService.getAllEntries(apiKey, tableName);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddEntry() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String[] columns = new String[]{"col_1", "col_2"};
        String[] values = new String[]{"val_1", "val_2"};
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        entryService.addEntry(apiKey, tableName, columns, values);
        Mockito.verify(entryRepository, Mockito.times(1))
                .addEntry(expectedTableName, columns, values);
    }

    @Test
    public void testDeleteEntries() {
        String apiKey = "testapikey";
        String tableName = "testtablename";
        String columnName = "test_col";
        String value = "test_val";
        String expectedTableName = ServiceUtils.getTableName(apiKey, tableName);
        entryService.deleteEntries(apiKey, tableName, columnName, value);
        Mockito.verify(entryRepository, Mockito.times(1))
                .removeEntries(expectedTableName, columnName, value);
    }

}