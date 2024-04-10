package org.eduardomaravill.java.jdbc.repository;


import org.eduardomaravill.java.jdbc.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TRepositoryImpl implements Repository<User>{

    private Connection conn;

    public TRepositoryImpl() {
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<User> readAll() throws SQLException{
        List<User> users = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT*FROM users")){
            while (rs.next()){
                users.add(createUser(rs));
            }
        }
        return users;
    }

    @Override
    public User byId(Long id) throws SQLException{
        User u= null;
        try(PreparedStatement stmt  = conn.prepareStatement("SELECT * FROM users WHERE idusers=?")){
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    u = createUser(rs);
                }
            }
        }
        return u;
    }

    @Override
    public User save(User user) throws SQLException{
        String sql;
        if (user.getId() != null && user.getId() > 0){
            sql = "UPDATE users SET username=?, password=? WHERE idusers=?";
        }else {
            sql = "INSERT INTO users(username, password, creation_date) VALUES(?,?,?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            if (user.getId() != null && user.getId() > 0){
                stmt.setLong(3, user.getId());
            }else {
                stmt.setTimestamp(3, Timestamp.valueOf(user.getDateTime()));
            }
            stmt.executeUpdate();
            if (user.getId() == null){
                try (ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        user.setId(rs.getLong(1));
                    }
                }
            }
        }
        return user;
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE idusers=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private User createUser(ResultSet rs) throws SQLException{
        User u = new User();
        u.setId(rs.getLong("idusers"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setDateTime(rs.getTimestamp("creation_date").toLocalDateTime());
        return u;
    }
}
