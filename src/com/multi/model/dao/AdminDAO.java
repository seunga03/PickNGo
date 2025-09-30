package com.multi.model.dao;

import com.multi.model.dto.adminDTO.Travel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
//            System.out.println(prop);
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
                travel.setTitle(rs.getString("title"));
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

    public int updateTravel(Connection conn, Travel travel) {

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateTravel");

        try {
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, travel.getTitle());
            pstmt.setString(2, travel.getPhone());
            pstmt.setString(3, travel.getAddress());
            pstmt.setString(4, travel.getDescription());
            pstmt.setString(5, travel.getDistrict());
            pstmt.setLong(6,travel.getNo());
            result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
        }

    }

    public int deleteTravel(Connection conn, long no) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteTravel");

        try {
            pstmt= conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
        }
    }

    public int insertLotOfTravel(Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertLotOfTravel");

        InputStream is = AdminDAO.class.getResourceAsStream("/travel.csv");
        CSVReader reader = new CSVReader(new InputStreamReader(is));

        try {
            pstmt = conn.prepareStatement(sql);

            String[] row;
            reader.readNext(); // 헤더 스킵
            while ((row = reader.readNext()) != null) {
                pstmt.setInt(1, Integer.parseInt(row[0]));
                pstmt.setString(2, row[1]);
                pstmt.setString(3, row[2]);
                pstmt.setString(4, row[3]);
                pstmt.setString(5, row[4]);
                pstmt.setString(6, row[5]);

                result += pstmt.executeUpdate();

            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
        }


    }
}
