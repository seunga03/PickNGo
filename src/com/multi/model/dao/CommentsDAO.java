package com.multi.model.dao;

import com.multi.model.dto.CommentsDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.multi.common.JDBCConnect.close;

public class CommentsDAO {

    private Properties prop = new Properties();

    public CommentsDAO() {

        try {
            prop.load(CommentsDAO.class.getResourceAsStream("/query.properties"));
//            System.out.println(prop);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insertComment(Connection conn, CommentsDTO comment) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertComment");

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1,comment.getNo());
            pstmt.setString(2,comment.getUser_Id());
            pstmt.setString(3,comment.getContent());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        return result;
    }

    public ArrayList<CommentsDTO> selectAllComment(Connection conn, Long no) {

        ArrayList<CommentsDTO> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectAllComment");
        System.out.println("sql : " + sql + "");
        System.out.println("no : " + no + "");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CommentsDTO comment = new CommentsDTO();
                comment.setNo(rs.getLong("no"));
                comment.setUser_Id(rs.getString("user_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                comment.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                list.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }
}
