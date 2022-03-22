package it.me.backyou.repository.query;

import it.me.backyou.controller.exception.ColumnAlreadyExistException;
import it.me.backyou.controller.exception.InvalidArgumentException;
import it.me.backyou.controller.exception.NoSuchColumnException;
import it.me.backyou.controller.exception.NoSuchTableException;
import it.me.backyou.controller.exception.TableAlreadyExistException;
import it.me.backyou.controller.exception.UnknownArgumentException;
import it.me.backyou.controller.exception.UnknownException;
import it.me.backyou.repository.query.exception.EmptyResultSetException;
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
        exceptionMap.put("42804", new InvalidArgumentException());
    }

    public void execute(final String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw exceptionMap.getOrDefault(e.getSQLState(), new UnknownException());
        }
    }

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
