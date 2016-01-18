package ru.broject.stepic.web.jdbc.dao;

import ru.broject.stepic.web.jdbc.executor.Executor;
import ru.broject.stepic.web.jdbc.model.UserProfile;
import ru.broject.stepic.web.jdbc.support.DbType;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public class UserDaoImpl implements UserDao {

    private final Executor executor;
    private static UserDao userDao;
    private static final Object lock = new Object();

    private UserDaoImpl() {
        this.executor = Executor.getInstance();
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            synchronized (lock) {
                if (userDao == null) {
                    userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public void createTable() {
        String sql = "create table if not exists users (id bigint auto_increment, login varchar(256), " +
                "password varchar(256), primary key (id))";
        executor.execUpdate(sql);
    }

    @Override
    public void dropTable() {
        String sql = "drop table users";
        executor.execUpdate(sql);
    }

    @Override
    public UserProfile getUser(String login) {
        String sql = "select * from users where login = ?";
        Map<Integer, DbType<Object, Integer>> parameters = new HashMap<>();
        parameters.put(1, new DbType<>(login, Types.VARCHAR));
        return executor.execQuery(sql, resultSet -> {
                    if (resultSet.next()) {
                        String password = resultSet.getString("password");
                        return new UserProfile(login, password);
                    }
                    return null;
                },
                parameters);
    }

    @Override
    public void insertUser(UserProfile userProfile) {
        String sql = "insert into users(login, password) values (?, ?)";
        Map<Integer, DbType<Object, Integer>> parameters = new HashMap<>();
        parameters.put(1, new DbType<>(userProfile.getLogin(), Types.VARCHAR));
        parameters.put(2, new DbType<>(userProfile.getPass(), Types.VARCHAR));
        executor.execUpdate(sql, parameters);
    }

}
