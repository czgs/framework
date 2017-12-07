package com.dbutils.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String url = "jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String password = "root";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection();
        System.out.println(conn.getAutoCommit());
    }
}
