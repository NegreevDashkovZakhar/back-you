package it.me.backyou.query;

import it.me.backyou.controller.exception.ColumnAlreadyExistException;
import it.me.backyou.controller.exception.NoSuchColumnException;
import it.me.backyou.controller.exception.NoSuchTableException;
import it.me.backyou.controller.exception.TableAlreadyExistException;
import it.me.backyou.controller.exception.UnknownArgumentException;
import it.me.backyou.controller.exception.UnknownException;
import it.me.backyou.query.exception.EmptyResultSetException;
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
        exceptionMap.put("42703", new NoSuchColumnException());
        exceptionMap.put("42701", new ColumnAlreadyExistException());
        exceptionMap.put("42704", new UnknownArgumentException());
    }

    public Object getAllEntries(final String table) {
        try {
            String sql = "SELECT * FROM " + table + ";";
            ResultSet rs = statement.executeQuery(sql);
            return MapperUtils.mapAllRows(rs);
        } catch (SQLException e) {
            throw new UnknownException();
        } catch (EmptyResultSetException e) {
            return new Object[]{};
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

    public Object getTableHeaderData(final String tableName) {
        try {
            String sql = "SELECT column_name, data_type " +
                    "FROM information_schema.columns " +
                    "WHERE table_name = '" + tableName + "';";
            ResultSet rs = statement.executeQuery(sql);
            return MapperUtils.mapAllRows(rs);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        } catch (EmptyResultSetException e) {
            throw new NoSuchTableException();
        }
    }

    public void addColumn(final String tableName, final String columnName, final String columnType) {
        try {
            String sql = "ALTER TABLE " + tableName +
                    " ADD COLUMN " + columnName + " " + columnType + ";";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

    public void renameColumn(final String tableName, final String oldName, final String newName) {
        try {
            String sql = "ALTER TABLE " + tableName +
                    " RENAME COLUMN " + oldName + " TO " + newName + ";";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

    public void deleteColumn(final String tableName, final String columnName) {
        try {
            String sql = "ALTER TABLE " + tableName +
                    " DROP COLUMN " + columnName + ";";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

    public void changeColumnType(final String tableName, final String columnName, final String newType) {
        try {
            String sql = "ALTER TABLE " + tableName +
                    " ALTER COLUMN " + columnName + " TYPE " + newType +
                    " USING " + columnName + "::" + newType + ";";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }
}
