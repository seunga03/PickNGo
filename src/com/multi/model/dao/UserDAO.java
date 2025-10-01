package com.multi.model.dao;

import com.multi.model.dto.tmddk.User;
import com.multi.service.UserSession;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static com.multi.common.JDBCConnect.close;
import static com.multi.service.UserSession.setUser;

public class UserDAO {
    private Properties prop = new Properties();
    public UserDAO() {
        try {
            prop.load(UserDAO.class.getResourceAsStream("/query.properties"));
//            System.out.println(prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public User checkGeneral(Connection conn, String userId, String password) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("checkGeneral");

        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 로그인 성공
//                setUser();
                String id = rs.getString("user_id").trim();
                String name = rs.getString("name");
                String pw = rs.getString("password");
                String role = rs.getString("role");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

                return new User(id, role, name, pw, createdAt, updatedAt);
            } else {
                // 로그인 실패
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int signUp(Connection conn, User user) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("signUp");

        try {
            pstmt = conn.prepareStatement(sql);
            // INSERT INTO USERS VALUES(?, 'general', ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int editPassword(Connection conn, String newPassword) {
        int result = 0;
        PreparedStatement pstmt = null;
        User me = UserSession.getUser();
        String sql = prop.getProperty("editPassword");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, me.getUserId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int editName(Connection conn, String newName) {
        int result = 0;
        PreparedStatement pstmt = null;
        User me = UserSession.getUser();
        String sql = prop.getProperty("editName");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setString(2, me.getUserId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public User findById(Connection conn, String userId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = prop.getProperty("findById");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("user_id").trim();
                String name = rs.getString("name");
                String pw = rs.getString("password");
                String role = rs.getString("role");
                Timestamp cts = rs.getTimestamp("created_at");
                Timestamp uts = rs.getTimestamp("updated_at");
                LocalDateTime createdAt = cts != null ? cts.toLocalDateTime() : null;
                LocalDateTime updatedAt = uts != null ? uts.toLocalDateTime() : null;
                return new User(id, role, name, pw, createdAt, updatedAt);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
        }
    }

}
