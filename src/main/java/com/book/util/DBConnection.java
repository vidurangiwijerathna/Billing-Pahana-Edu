package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/pahanaedubilling?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Replace with your MySQL username if different
    private static final String PASSWORD = "Root@123456"; // Replace with your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Always return a new, fresh connection to avoid threading issues
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
