package com.multi.service;

import com.multi.model.dao.LikeDAO;
import com.multi.model.dao.TravelDAO;
import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.LikesDTO;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.Like;

import java.sql.Connection;
import java.util.ArrayList;

import static com.multi.common.JDBCConnect.*;
import static com.multi.common.JDBCConnect.rollback;

public class LikeService {
    private final LikeDAO likeDAO;
    public LikeService() {likeDAO = new LikeDAO();}

    public ArrayList<Like> selectAllLikes(String userId) {

        Connection conn = getConnection();
        ArrayList<Like> list = likeDAO.selectAllLikes(conn, userId);
        close(conn);

        return list;
    }

    public int insertLike(LikesDTO like) {

        Connection conn = getConnection();
        int result = likeDAO.insertLike(conn,like);
        if(result > 0 ) {
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }
}
