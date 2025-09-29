package com.multi.model.dao;

import com.multi.model.dto.Member;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.multi.common.JDBCConnect.close;

public class MemberDAO {
    private Properties prop = new Properties();

    public MemberDAO() {
        try {
            prop.load(MemberDAO.class.getResourceAsStream("/query.properties"));
            System.out.println(prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Member> selectAll(Connection conn) {
        ArrayList<Member> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectAll");

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // 쿼리 실행

            while (rs.next()) {

                // https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
                Member m = new Member();
                m.setUserNo(rs.getLong("userno"));

                m.setUserId(rs.getString("USERID"));
                m.setPassword(rs.getString("PASSWORD"));
                m.setUserName(rs.getString("USERNAME"));
                m.setGender(rs.getString("GENDER"));
                m.setAge(rs.getInt("AGE"));
                m.setEmail(rs.getString("EMAIL"));
                m.setPhone(rs.getString("PHONE"));
                m.setAddress(rs.getString("ADDRESS"));
                m.setHobby(rs.getString("HOBBY"));
                m.setEnrollDate(rs.getTimestamp("ENROLLDATE").toLocalDateTime());

                list.add(m);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(stmt);
        }

        return list;
    }

    public Member selectOne(Connection conn, String memberId) {
        Member m = new Member();
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectOne");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                // https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
                m.setUserNo(rs.getLong("userno"));

                m.setUserId(rs.getString("USERID"));
                m.setPassword(rs.getString("PASSWORD"));
                m.setUserName(rs.getString("USERNAME"));
                m.setGender(rs.getString("GENDER"));
                m.setAge(rs.getInt("AGE"));
                m.setEmail(rs.getString("EMAIL"));
                m.setPhone(rs.getString("PHONE"));
                m.setAddress(rs.getString("ADDRESS"));
                m.setHobby(rs.getString("HOBBY"));
                m.setEnrollDate(rs.getTimestamp("ENROLLDATE").toLocalDateTime());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(stmt);
        }

        return m;
    }

    public Member selectByName(Connection conn, String username) {
        Member m = new Member();
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectByName");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+username+"%");
            rs = pstmt.executeQuery();

            if (rs.next()) {

                // https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
                m.setUserNo(rs.getLong("userno"));

                m.setUserId(rs.getString("USERID"));
                m.setPassword(rs.getString("PASSWORD"));
                m.setUserName(rs.getString("USERNAME"));
                m.setGender(rs.getString("GENDER"));
                m.setAge(rs.getInt("AGE"));
                m.setEmail(rs.getString("EMAIL"));
                m.setPhone(rs.getString("PHONE"));
                m.setAddress(rs.getString("ADDRESS"));
                m.setHobby(rs.getString("HOBBY"));
                m.setEnrollDate(rs.getTimestamp("ENROLLDATE").toLocalDateTime());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(stmt);
        }

        return m;
    }

    public int insertMember(Connection conn, Member member) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getUserName());
            pstmt.setString(4, member.getGender());
            pstmt.setInt(5, member.getAge());
            pstmt.setString(6, member.getEmail());
            pstmt.setString(7, member.getPhone());
            pstmt.setString(8, member.getAddress());
            pstmt.setString(9, member.getHobby());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updateMember(Connection conn, Member member) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPhone());
            pstmt.setString(4, member.getAddress());
            pstmt.setString(5, member.getUserId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteMember(Connection conn, String s) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public ArrayList<Member> selectAllDeleteMember(Connection conn) {
        ArrayList<Member> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectAllDeleteMember");

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // 쿼리 실행

            while (rs.next()) {

                // https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
                Member m = new Member();
                m.setUserNo(rs.getLong("userno"));

                m.setUserId(rs.getString("USERID"));
                m.setPassword(rs.getString("PASSWORD"));
                m.setUserName(rs.getString("USERNAME"));
                m.setGender(rs.getString("GENDER"));
                m.setAge(rs.getInt("AGE"));
                m.setEmail(rs.getString("EMAIL"));
                m.setPhone(rs.getString("PHONE"));
                m.setAddress(rs.getString("ADDRESS"));
                m.setHobby(rs.getString("HOBBY"));
                m.setEnrollDate(rs.getTimestamp("ENROLLDATE").toLocalDateTime());

                list.add(m);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(stmt);
        }

        return list;
    }
}
