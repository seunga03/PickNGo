package com.multi.service;

import com.multi.model.dao.UserDAO;
import com.multi.model.dto.tmddk.User;

import java.sql.Connection;

import static com.multi.common.JDBCConnect.*;

public class GeneralService {

    private final UserDAO userDAO;

    public GeneralService() {
        userDAO = new UserDAO();
    }

    public boolean checkGeneral(String userId, String password) {
        Connection conn = null;
        try {
            conn = getConnection();
            boolean result = userDAO.checkGeneral(conn, userId, password);
            // SELECT면 트랜잭션 처리 불필요 (남겨두고 싶다면 조건만 바로잡기)
            // if (result) commit(conn); else rollback(conn);

//            if (result = true) {
//                commit(conn);
//            } else {
//                rollback(conn);
//            }

            return result;
        } finally {
            close(conn);
        }
    }

    public int signUp(User user) {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        int result =  userDAO.signUp(conn, user);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }
}
