package org.edumaravill.jdbc;

import java.sql.*;

public class BasicConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_java_example";
        String username = "root";
        String password = "root";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
