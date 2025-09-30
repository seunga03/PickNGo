package com.multi.model.dao;

import com.multi.model.dto.TravelDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.multi.common.JDBCConnect.*;

public class TravelDAO {

    Properties prop = new Properties();

    public TravelDAO() {

        try {
            prop.load(TravelDAO.class.getResourceAsStream("/query.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TravelDTO> selectBySearch(Connection conn, String search) {

        ArrayList<TravelDTO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectBySearch");


        try {
            TravelDTO t = new TravelDTO();
            pstmt = conn.prepareStatement(sql);
            String keyword = "%" + search + "%";
            pstmt.setString(1, keyword);
            pstmt.setString(2, keyword);
            pstmt.setString(3, keyword);
            pstmt.setString(4, keyword);
            pstmt.setString(5, keyword);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                t = new TravelDTO();
                t.setNo(rs.getLong("no"));
                t.setDistrict(rs.getString("district"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setAddress(rs.getString("address"));
                t.setPhone(rs.getString("phone"));
//                t.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
//                t.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
                list.add(t);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(rs);
            close(pstmt);

        }
        return list;
    }
}
