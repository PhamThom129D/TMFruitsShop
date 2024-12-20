package com.example.tmfruitsshop.Service;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDatabase {
    private static final String url = "jdbc:mysql://localhost:3306/tmfruitsshop";
    private static final String username = System.getenv("DB_USER");
    private static final String password = System.getenv("DB_PASS");
//private static final String username = "root";
//    private static final String password = "1209";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return java.sql.DriverManager.getConnection(url, username, password);

    }
}
