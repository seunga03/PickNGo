package com.multi.service;

import com.multi.model.dao.UserDAO;
import com.multi.model.dto.tmddk.User;

import java.sql.Connection;

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
            int updated = userDAO.editName(conn, newName);
            if (updated > 0) {
                // DB에서 최신값 재조회
                String uid = UserSession.getUser().getUserId();
                User fresh = userDAO.findById(conn, uid);
                // 세션 갱신
                UserSession.setUser(fresh);
                return fresh;
            }
            return null;
        } finally {
            close(conn);
        }
    }

}
