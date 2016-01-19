package ru.broject.stepic.web.orm.service;

import ru.broject.stepic.web.orm.model.UserProfile;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public interface UserService {

    void addUser(UserProfile userProfile);

    UserProfile getUser(String login);
}
