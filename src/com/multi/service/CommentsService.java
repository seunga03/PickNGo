package com.multi.service;

import com.multi.model.dao.CommentsDAO;
import com.multi.model.dto.CommentsDTO;

import java.sql.Connection;
import java.util.ArrayList;

import static com.multi.common.JDBCConnect.*;

public class CommentsService {

    private final CommentsDAO commentDAO;

    public CommentsService() { commentDAO = new CommentsDAO();}

    public int insertComment(CommentsDTO comment) {

        Connection conn = getConnection();
        int result = commentDAO.insertComment(conn,comment);
        if(result > 0 ) {
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
     }

    public ArrayList<CommentsDTO> selectAllComment(Long no) {

        Connection conn = getConnection();
        ArrayList<CommentsDTO> list = commentDAO.selectAllComment(conn,no);
        close(conn);
        return list;
    }
}
