package com.multi.service;

import com.multi.model.dao.MemberDAO;

import com.multi.model.dto.Member;
import java.sql.Connection;
import java.util.ArrayList;

import static com.multi.common.JDBCConnect.*;

public class MemberService {
    private final MemberDAO memberDAO;
    public MemberService() {
        memberDAO = new MemberDAO(); // 서비스가 하나 생성될 때 dao가 생성됨
    }

    public ArrayList<Member> selectAll() {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        ArrayList<Member> list =  memberDAO.selectAll(conn);
        close(conn);
        return list;
    }

    public Member selectOne(String memberId) {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        Member member =  memberDAO.selectOne(conn, memberId);
        close(conn);
        return member;
    }

    public Member selectByName(String username) {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        Member member =  memberDAO.selectByName(conn, username);
        close(conn);
        return member;
    }

    public int insertMember(Member member) {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        int result =  memberDAO.insertMember(conn, member);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int updateMember(Member member) {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        int result =  memberDAO.updateMember(conn, member);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int deleteMember(String s) {
        Connection conn = getConnection();
        int result = memberDAO.deleteMember(conn, s);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public ArrayList<Member> selectAllDeleteMember() {
        Connection conn = getConnection(); // common에 만든 거 가져오는 것으로 대체
        ArrayList<Member> list =  memberDAO.selectAllDeleteMember(conn);
        close(conn);
        return list;
    }
}
