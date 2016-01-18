package ru.broject.stepic.web.jdbc.support;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public class DbType<V, Integer> {

    private V value;
    private Integer sqlType;

    public DbType(V value, Integer sqlType) {
        this.value = value;
        this.sqlType = sqlType;
    }

    public V getValue() {
        return value;
    }

    public Integer getSqlType() {
        return sqlType;
    }
}
