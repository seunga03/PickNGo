package com.multi.service;

import com.multi.model.dao.UserDAO;
import com.multi.model.dto.tmddk.User;

import java.sql.Connection;
import java.sql.SQLException;

import static com.multi.common.JDBCConnect.*;
import static com.multi.common.JDBCConnect.close;

public class EditProfileService {

    private final UserDAO userDAO;

    public EditProfileService() {
        userDAO = new UserDAO();
    }

    public int editName(String newName) {
        Connection conn = getConnection();
        int result = userDAO.editName(conn,newName);
        if(result>0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int editPassword(String newPassword) {
        Connection conn = getConnection();
        int result = userDAO.editPassword(conn,newPassword);
        if(result>0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public User editNameAndRefresh(String newName) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            int updated = userDAO.editName(conn, newName);
            if (updated > 0) {
                // DB에서 최신값 재조회
                String uid = UserSession.getUser().getUserId();
                User fresh = userDAO.findById(conn, uid);
                // 세션 갱신
                conn.commit();
                UserSession.setUser(fresh);
                return fresh;
            } else {
                conn.rollback();
                return null;
            }
        } catch (Exception e) {
            try {

                if (conn != null) conn.rollback();
            } catch (Exception ignore) {
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (Exception ignore) {
            }
            close(conn);
        }
    }

    public User editPasswordAndRefresh(String newPassword) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            int updated = userDAO.editPassword(conn, newPassword);
            if (updated > 0) {
                // DB에서 최신값 재조회
                String uid = UserSession.getUser().getUserId();
                User fresh = userDAO.findById(conn, uid);
                // 세션 갱신
                conn.commit();
                UserSession.setUser(fresh);
                return fresh;
            }else {
                conn.rollback();
                return null;
            }
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                } } catch (Exception ignore) {}
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                } } catch (Exception ignore) {}
            close(conn);
        }
    }
}
