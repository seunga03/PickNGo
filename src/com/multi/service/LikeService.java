package com.multi.service;

import com.multi.model.dao.LikeDAO;
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
    public ArrayList<TravelDTO> selectLikedTravelsByUserId(String userId) {
        Connection conn = getConnection();
        ArrayList<TravelDTO> list = likeDAO.selectLikedTravelsByUserId(conn, userId);
        close(conn);
        return list;
    }
    public int insertLike(LikesDTO like) {
        Connection conn = getConnection();

        // 1. 즐겨찾기가 이미 존재하는지 확인
        if (likeDAO.isLikeExists(conn, like)) {
            close(conn);
            return -1;
        }

        // 2. 존재하지 않으면 삽입
        int result = likeDAO.insertLike(conn, like);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result; // 성공 시 1, 실패 시 0 반환
    }
}
