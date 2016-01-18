package ru.broject.stepic.web.jdbc.dao;

import ru.broject.stepic.web.jdbc.model.UserProfile;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public interface UserDao {

    void createTable();

    void dropTable();

    UserProfile getUser(String login);

    void insertUser(UserProfile userProfile);
}
