package com.multi.service;

import com.multi.model.dao.UserDAO;

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
}
