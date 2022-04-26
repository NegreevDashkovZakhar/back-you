package it.me.backyou.storage.repository.query;

import it.me.backyou.storage.repository.query.MapperUtils;
import it.me.backyou.storage.repository.query.exception.EmptyResultSetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MapperUtilsTest {
    @Test
    public void testMapAllRowsNormalObject() throws SQLException, EmptyResultSetException {
        ResultSetMetaData rsmd = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(rsmd.getColumnCount()).thenReturn(2);
        Mockito.when(rsmd.getColumnName(1)).thenReturn("name");
        Mockito.when(rsmd.getColumnName(2)).thenReturn("age");

        try (ResultSet rs = Mockito.mock(ResultSet.class)) {
            Mockito.when(rs.next()).thenReturn(true, true, false);
            Mockito.when(rs.getObject(1)).thenReturn("John", "Marry");
            Mockito.when(rs.getObject(2)).thenReturn(24, 20);
            Mockito.when(rs.getMetaData()).thenReturn(rsmd);

            List<Map<String, Object>> expected = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("name", "John");
            map.put("age", 24);
            expected.add(map);
            map = new HashMap<>();
            map.put("name", "Marry");
            map.put("age", 20);
            expected.add(map);

            Object actual = MapperUtils.mapAllRows(rs);
            Assertions.assertEquals(expected, actual);
        }
    }
}