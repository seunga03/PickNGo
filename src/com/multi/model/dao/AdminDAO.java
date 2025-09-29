package com.multi.model.dao;

import com.multi.model.dto.adminDTO.Travel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.multi.common.JDBCConnect.*;

public class AdminDAO {

    Properties prop = new Properties();

    public AdminDAO() {

        try {
            prop.load(AdminDAO.class.getResourceAsStream("/adminQuery.properties"));
            System.out.println(prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkAdmin(Connection conn, String admin_id, String admin_pw) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("checkAdmin");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin_id);
            pstmt.setString(2, admin_pw);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(rs);
            close(pstmt);
        }


    }

    public ArrayList<Travel> selectAll(Connection conn) {
        ArrayList<Travel> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectAll");

        try {
            pstmt= conn.prepareStatement(sql);
            rs=pstmt.executeQuery(sql);
            while(rs.next()) {
                Travel travel = new Travel();
                travel.setNo(rs.getLong("no"));
                travel.setDistrict(rs.getString("district"));
                travel.setTitel(rs.getString("title"));
                travel.setDescription(rs.getString("description"));
                travel.setAddress(rs.getString("address"));
                travel.setPhone(rs.getString("phone"));
                travel.setCreated_at(rs.getTimestamp("created_at"));
                travel.setUpdated_at(rs.getTimestamp("updated_at"));
                list.add(travel);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(rs);
            close(pstmt);
        }


    }
}
