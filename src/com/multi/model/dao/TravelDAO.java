package com.multi.model.dao;

import com.multi.model.dto.TravelDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;

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

    public TravelDTO showDetail(Connection conn, int no) {
        TravelDTO t = new TravelDTO();
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectDetail");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                t.setNo(rs.getLong("no"));
                t.setTitle(rs.getString("title"));
                t.setDistrict(rs.getString("district"));
                t.setDescription(rs.getString("description"));
                t.setAddress(rs.getString("address"));
                t.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(stmt);
        }
        return t;
    }

    public ArrayList<TravelDTO> selectAll(Connection conn) {
        ArrayList<TravelDTO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectAll");
        try {
            TravelDTO t = new TravelDTO();
            pstmt = conn.prepareStatement(sql);
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
        }
        return list;
    }

    public ArrayList<TravelDTO> selectByDistrict(Connection conn, int district_no) {

        ArrayList<TravelDTO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = prop.getProperty("selectByDistrict");
        try{
            TravelDTO t = new TravelDTO();
            pstmt = conn.prepareStatement(sql);

            // 수도권:1, 충청권:2, 경상권:3, 전라권:4, 강원권:5, 제주권:6
            switch (district_no) {
                case 1: pstmt.setString(1,"수도권"); break;
                case 2: pstmt.setString(1,"충청권"); break;
                case 3: pstmt.setString(1,"경상권"); break;
                case 4: pstmt.setString(1,"전라권"); break;
                case 5: pstmt.setString(1,"강원권"); break;
                case 6: pstmt.setString(1,"제주권"); break;

            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                t = new TravelDTO();
                t.setNo(rs.getLong("no"));
                t.setDistrict(rs.getString("district"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setAddress(rs.getString("address"));
                t.setPhone(rs.getString("phone"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }



        return list;

    }
}
