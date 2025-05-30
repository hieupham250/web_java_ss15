package com.data.connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ss15";
    private static String user = "root";
    private static String password = "12345678";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement cs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}