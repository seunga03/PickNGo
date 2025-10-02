package com.multi.model.dao;

import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.LikesDTO;
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

    public int insertLike(Connection conn, LikesDTO like) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertLike");

        try {
            System.out.println("inserLike sql : " + sql + "");
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,like.getUser_Id());
            pstmt.setLong(2,like.getNo());


            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        return result;
    }

    public boolean isLikeExists(Connection conn, LikesDTO like) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM LIKES WHERE USER_ID = ? AND NO = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, like.getUser_Id());
            pstmt.setLong(2, like.getNo());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
        }
        return false;
    }

    public ArrayList<TravelDTO> selectLikedTravelsByUserId(Connection conn, String userId) {
        ArrayList<TravelDTO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectLikedTravelsByUserId");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO travel = new TravelDTO();
                travel.setNo(rs.getLong("no"));
                travel.setTitle(rs.getString("title"));
                travel.setDistrict(rs.getString("district"));
                list.add(travel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
        }
        return list;
    }


}
