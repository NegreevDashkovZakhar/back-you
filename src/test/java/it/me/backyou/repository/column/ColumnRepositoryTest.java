package it.me.backyou.repository.column;

import it.me.backyou.repository.query.QueryExecutor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ColumnRepositoryTest {
    private static ColumnRepository columnRepository;
    private static QueryExecutor queryExecutor;

    @BeforeAll
    public static void setUp() {
        queryExecutor = Mockito.mock(QueryExecutor.class);
        columnRepository = new ColumnRepository(queryExecutor);
    }

    @Test
    public void testAddColumn() {
        String tableName = "sample__table";
        String columnName = "col_name";
        String columnType = "int";
        String expectedSQL = "ALTER TABLE sample__table ADD COLUMN col_name int;";
        columnRepository.addColumn(tableName, columnName, columnType);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testRenameColumn() {
        String tableName = "sample__table";
        String columnName = "col_name";
        String newColumnName = "wow_column";
        String expectedSQL = "ALTER TABLE sample__table RENAME COLUMN col_name TO wow_column;";
        columnRepository.renameColumn(tableName, columnName, newColumnName);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testDeleteColumn() {
        String tableName = "sample__table";
        String columnName = "col_name";
        String expectedSQL = "ALTER TABLE sample__table DROP COLUMN col_name;";
        columnRepository.deleteColumn(tableName, columnName);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testChangeColumnType() {
        String tableName = "sample__table";
        String columnName = "col_name";
        String newColumnType = "int";
        String expectedSQL = "ALTER TABLE sample__table ALTER COLUMN col_name TYPE int USING col_name::int;";
        columnRepository.changeColumnType(tableName, columnName, newColumnType);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }

    @Test
    public void testChangeColumnDefault() {
        String tableName = "sample__table";
        String columnName = "col_name";
        String newColumnDefault = "default_val";
        String expectedSQL = "ALTER TABLE sample__table ALTER COLUMN col_name SET DEFAULT 'default_val';";
        columnRepository.changeColumnDefault(tableName, columnName, newColumnDefault);
        Mockito.verify(queryExecutor, Mockito.times(1)).execute(expectedSQL);
    }
}