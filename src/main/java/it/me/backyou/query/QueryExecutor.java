package it.me.backyou.query;

import it.me.backyou.controller.exception.NoSuchTableException;
import it.me.backyou.controller.exception.TableAlreadyExistException;
import it.me.backyou.controller.exception.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Component
public class QueryExecutor {
    private final Map<String, ResponseStatusException> exceptionMap;
    private final Statement statement;

    @Autowired
    public QueryExecutor(final Environment env) throws SQLException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        statement = dataSource.getConnection().createStatement();

        exceptionMap = new HashMap<>();
        exceptionMap.put("42P07", new TableAlreadyExistException());
        exceptionMap.put("42P01", new NoSuchTableException());
    }

    public Object getAllEntries(final String table) {
        try {
            String sql = "SELECT * FROM " + table + ";";
            ResultSet rs = statement.executeQuery(sql);
            return MapperUtils.mapAllRows(rs);
        } catch (SQLException e) {
            throw new UnknownException();
        }
    }

    public void createTable(final String tableName) {
        try {
            String sql = "CREATE TABLE " + tableName + "(" +
                    "id serial PRIMARY KEY" +
                    ");";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

    public void dropTable(final String tableName) {
        try {
            String sql = "DROP TABLE " + tableName + ";";
            statement.execute(sql);
        } catch (SQLException e) {
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

    public void renameTable(final String oldName, final String newName) {
        try {
            String sql = "ALTER TABLE " + oldName +
                    " RENAME TO " + newName + ";";
            statement.execute(sql);
        } catch (SQLException e) {
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

}
