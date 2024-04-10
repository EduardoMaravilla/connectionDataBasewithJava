package org.eduardomaravill.java.jdbc.service;

import org.eduardomaravill.java.jdbc.models.User;
import org.eduardomaravill.java.jdbc.repository.Repository;
import org.eduardomaravill.java.jdbc.repository.TRepositoryImpl;
import org.eduardomaravill.java.jdbc.useful.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceImpl implements Service{
    private Repository<User> userRepository;

    public ServiceImpl() {
        this.userRepository = new TRepositoryImpl();
    }

    @Override
    public List<User> readAll() throws SQLException {
        try(Connection conn = ConnectionDB.getConnection()){
            userRepository.setConn(conn);
            return userRepository.readAll();
        }
    }

    @Override
    public User byId(Long id) throws SQLException {
        try(Connection conn = ConnectionDB.getConnection()){
            userRepository.setConn(conn);
            return userRepository.byId(id);
        }
    }

    @Override
    public User save(User u) throws SQLException {
        try(Connection conn = ConnectionDB.getConnection()){
            userRepository.setConn(conn);
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            User user = null;
            try{
                user = userRepository.save(u);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                System.out.println("Server error");
                e.printStackTrace();
            }
            return user;
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
          try(Connection conn = ConnectionDB.getConnection()){
              userRepository.setConn(conn);
              if (conn.getAutoCommit()){
                  conn.setAutoCommit(false);
              }
              try{
                  userRepository.delete(id);
                  conn.commit();
              }catch (SQLException e){
                  conn.rollback();
                  e.printStackTrace();
              }
          }
    }
}
