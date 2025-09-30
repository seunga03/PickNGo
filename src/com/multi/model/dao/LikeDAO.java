package com.multi.model.dao;

import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.Like;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.multi.common.JDBCConnect.close;

public class LikeDAO {

    Properties prop = new Properties();

    public LikeDAO() {

        try {
            prop.load(LikeDAO.class.getResourceAsStream("/query.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Like> selectAllLikes(Connection conn, String userId) {
        ArrayList<Like> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectAllLikes");
        try {
            Like like = new Like();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                like = new Like();

                like.setSeq(rs.getLong("seq"));
                like.setUserId(rs.getString("user_id"));
                like.setNo(rs.getString("no"));
                like.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
                like.setUpdateAt(rs.getTimestamp("updated_at").toLocalDateTime());

                list.add(like);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
