package org.eduardomaravill.java.useful;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDB {
    private static String url= "jdbc:mysql://localhost:3306/db_java_example";
    private static String username = "root";
    private static String password = "root";
    private static BasicDataSource pool;

    private static BasicDataSource getInstancePool() throws SQLException{
        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
            pool.setTestOnBorrow(true);
            pool.setTestOnReturn(true);
        }
        return pool;
    }

    public static Connection getPoolConnection() throws SQLException{
        return getInstancePool().getConnection();
    }
}
