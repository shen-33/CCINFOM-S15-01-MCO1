package com.parkincinema.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // database name
    private static final String URL = "jdbc:mysql://localhost:3306/ccinfom_s15_01"; // named the schema as "parkin_db", just replace if you have your own

    // database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "admin"; // just replace with your own password if you want to test

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
