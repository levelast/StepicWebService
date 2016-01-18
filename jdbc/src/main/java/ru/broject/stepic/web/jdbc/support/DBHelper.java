package ru.broject.stepic.web.jdbc.support;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public class DBHelper {

    private static Connection connection = getH2Connection();

    private DBHelper() {
    }

    public static Connection getConnection() {
        return connection;
    }

    private static Connection getH2Connection() {
        String url = "jdbc:h2:./h2db";
        String user = "test";
        String pass = "test";

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);
        ds.setUser(user);
        ds.setPassword(pass);

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
