package com.multi.service;

import com.multi.model.dao.LikeDAO;
import com.multi.model.dao.TravelDAO;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.Like;

import java.sql.Connection;
import java.util.ArrayList;

import static com.multi.common.JDBCConnect.close;
import static com.multi.common.JDBCConnect.getConnection;

public class LikeService {
    private final LikeDAO likeDAO;
    public LikeService() {likeDAO = new LikeDAO();}

    public ArrayList<Like> selectAllLikes(String userId) {

        Connection conn = getConnection();
        ArrayList<Like> list = likeDAO.selectAllLikes(conn, userId);
        close(conn);

        return list;
    }
}
