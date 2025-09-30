package com.multi.model.dao;

import com.multi.model.dto.tmddk.User;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static com.multi.common.JDBCConnect.close;

public class UserDAO {
    private Properties prop = new Properties();
    public UserDAO() {
        try {
            prop.load(UserDAO.class.getResourceAsStream("/query.properties"));
            System.out.println(prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public boolean checkGeneral(Connection conn, String userId, String password) {
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
                return true;
            } else {
                // 로그인 실패
                return false;
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
}
