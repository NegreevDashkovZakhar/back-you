package it.me.backyou.storage.repository.query;

import it.me.backyou.storage.controller.exception.ColumnAlreadyExistException;
import it.me.backyou.storage.controller.exception.InvalidArgumentException;
import it.me.backyou.storage.controller.exception.NoSuchColumnException;
import it.me.backyou.storage.controller.exception.NoSuchTableException;
import it.me.backyou.storage.controller.exception.TableAlreadyExistException;
import it.me.backyou.storage.controller.exception.UnknownArgumentException;
import it.me.backyou.storage.controller.exception.UnknownException;
import it.me.backyou.storage.repository.query.exception.EmptyResultSetException;
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

/**
 * Class executing and parsing given SQL requests
 * Throws errors from specified table
 */
@Component
public class QueryExecutor {
    private final Map<String, ResponseStatusException> exceptionMap;
    private final Statement statement;

    /**
     * Default constructor creating statement for sql execution and exception map
     * Environment variable is autowired and used to create statement object
     *
     * @param env given environment for sql execution
     * @throws SQLException when could not create statement (should not be thrown)
     */
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
        exceptionMap.put("42804", new InvalidArgumentException());
    }

    /**
     * Method executing given sql query
     * Does not return any results
     *
     * @param sql sql query for execution
     */
    public void execute(final String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

    /**
     * Method executing given sql query and parsing returned result
     * Used for Select and similar queries
     *
     * @param sql sql query for execution
     * @return object containing parsed data from sql quering result
     */
    public Object executeToObject(final String sql) {
        try {
            ResultSet rs = statement.executeQuery(sql);
            return MapperUtils.mapAllRows(rs);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        } catch (EmptyResultSetException e) {
            return new Object[]{};
        }
    }
}
