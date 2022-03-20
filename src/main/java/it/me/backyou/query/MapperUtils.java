package it.me.backyou.query;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtils {
    public static Object mapAllRows(final ResultSet rs) throws SQLException {
        List<Object> result = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        do {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= columnCount; ++i) {
                map.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            result.add(map);
        } while (rs.next());
        return result;
    }
}
