package it.me.backyou.query;

import it.me.backyou.query.exception.EmptyResultSetException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtils {
    public static Object mapAllRows(final ResultSet rs) throws SQLException, EmptyResultSetException {
        int rowsCount = 0;
        List<Object> result = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rs.next()) {
            rowsCount++;
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= columnCount; ++i) {
                map.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            result.add(map);
        }
        if (rowsCount == 0) {
            throw new EmptyResultSetException("Result set is empty");
        }
        return result;
    }
}
