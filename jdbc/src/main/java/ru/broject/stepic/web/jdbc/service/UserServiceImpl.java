package ru.broject.stepic.web.jdbc.service;

import ru.broject.stepic.web.jdbc.dao.UserDao;
import ru.broject.stepic.web.jdbc.dao.UserDaoImpl;
import ru.broject.stepic.web.jdbc.model.UserProfile;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private static UserService userService = null;
    private static final Object lock = new Object();

    private UserServiceImpl() {
        this.userDao = UserDaoImpl.getInstance();
    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (lock) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public void addUser(UserProfile userProfile) {
        userDao.insertUser(userProfile);
    }

    @Override
    public UserProfile getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    public void createTable() {
        userDao.createTable();
    }

    @Override
    public void dropTable() {
        userDao.dropTable();
    }
}
