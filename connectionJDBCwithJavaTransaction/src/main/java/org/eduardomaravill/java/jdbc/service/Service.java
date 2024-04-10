package org.eduardomaravill.java.jdbc.service;

import org.eduardomaravill.java.jdbc.models.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<User> readAll() throws SQLException;
    User byId(Long id) throws SQLException;
    User save(User u) throws SQLException;
    void delete(Long id) throws SQLException;
}
