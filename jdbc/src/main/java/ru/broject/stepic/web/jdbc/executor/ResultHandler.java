package ru.broject.stepic.web.jdbc.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
@FunctionalInterface
public interface ResultHandler<T> {

    T handle(ResultSet resultSet) throws SQLException;
}
