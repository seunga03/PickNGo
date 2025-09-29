package com.multi.common;

import com.multi.model.dao.MemberDAO;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCConnect {
    private static Connection conn;
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Properties prop = new Properties();
                prop.load(MemberDAO.class.getResourceAsStream("/driver.properties"));
                System.out.println(prop);

                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");

                conn = DriverManager.getConnection(url, user, password);
                System.out.println(conn);
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return conn;

    }

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {

                System.out.println("닫기 -- Connection");
                conn.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {

                System.out.println("닫기 -- Statement");
                stmt.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {

                System.out.println("닫기 -- ResultSet");
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {

                System.out.println("commit ----------------------");
                conn.commit();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {

                System.out.println("rollback --------------------");
                conn.rollback();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
