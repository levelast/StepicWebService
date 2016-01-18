package ru.broject.stepic.web.jdbc.executor;

import ru.broject.stepic.web.jdbc.support.DBHelper;
import ru.broject.stepic.web.jdbc.support.DbType;

import java.sql.*;
import java.util.Map;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public class Executor {

    private final Connection connection;
    private static Executor executor;
    private static final Object lock = new Object();

    private Executor() {
        this.connection = DBHelper.getConnection();
    }

    public static Executor getInstance() {
        if (executor == null) {
            synchronized (lock) {
                if (executor == null) {
                    executor = new Executor();
                }
            }
        }
        return executor;
    }

    public void execUpdate(String update) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execUpdate(String update, Map<Integer, DbType<Object, Integer>> parameters) {
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            for (Map.Entry<Integer, DbType<Object, Integer>> entry : parameters.entrySet()) {
                stmt.setObject(entry.getKey(), entry.getValue().getValue(), entry.getValue().getSqlType());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            try (ResultSet rs = stmt.getResultSet()) {
                T value = handler.handle(rs);
                return value;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler, Map<Integer, DbType<Object, Integer>> parameters) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (Map.Entry<Integer, DbType<Object, Integer>> entry : parameters.entrySet()) {
                stmt.setObject(entry.getKey(), entry.getValue().getValue(), entry.getValue().getSqlType());
            }
            stmt.executeQuery();
            try (ResultSet rs = stmt.getResultSet()) {
                T value = handler.handle(rs);
                return value;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
