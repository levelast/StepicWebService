package ru.broject.stepic.web.jdbc.service;

import ru.broject.stepic.web.jdbc.model.UserProfile;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public interface UserService {

    void createTable();

    void dropTable();

    void addUser(UserProfile userProfile);

    UserProfile getUser(String login);
}
