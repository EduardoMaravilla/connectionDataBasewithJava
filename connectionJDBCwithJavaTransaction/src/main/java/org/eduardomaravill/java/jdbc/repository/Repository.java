package org.eduardomaravill.java.jdbc.repository;

import org.eduardomaravill.java.jdbc.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void setConn(Connection conn);
    List<T> readAll() throws SQLException;
    T byId(Long id) throws SQLException;
    User save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
}
