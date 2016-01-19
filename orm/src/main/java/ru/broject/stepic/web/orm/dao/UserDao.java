package ru.broject.stepic.web.orm.dao;

import ru.broject.stepic.web.orm.model.UserProfile;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public interface UserDao {

    UserProfile getUser(String login);

    void insertUser(UserProfile userProfile);
}
