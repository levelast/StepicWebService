package ru.broject.stepic.web.jdbc.model;

import java.io.Serializable;

/**
 * Created by vyacheslav.svininyh on 12.01.2016.
 */
public class UserProfile implements Serializable {

    private String login;
    private String pass;
    private String email;

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
