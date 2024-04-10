package org.eduardomaravill.java.repository;

import org.eduardomaravill.java.models.User;
import org.eduardomaravill.java.useful.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TRepositorioImpl implements Repository<User>{

    private Connection getConnection() throws SQLException{
        return ConnectionDB.getPoolConnection();
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT*FROM users")){
            while (rs.next()){
                users.add(createUser(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User byId(Long id) {
        User u= null;
        try(Connection conn =getConnection();
            PreparedStatement stmt  = conn.prepareStatement("SELECT * FROM users WHERE idusers=?")){
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    u = createUser(rs);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void save(User user) {
        String sql;
        if (user.getId() != null && user.getId() > 0){
            sql = "UPDATE users SET username=?, password=? WHERE idusers=?";
        }else {
            sql = "INSERT INTO users(username, password, creation_date) VALUES(?,?,?)";
        }
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            if (user.getId() != null && user.getId() > 0){
                stmt.setLong(3, user.getId());
            }else {
                stmt.setTimestamp(3, Timestamp.valueOf(user.getDateTime()));
            }
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE idusers=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
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
