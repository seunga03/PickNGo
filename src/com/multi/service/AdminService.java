package com.multi.service;


import com.multi.model.dao.AdminDAO;
import com.multi.model.dto.adminDTO.Travel;

import java.sql.Connection;
import java.util.ArrayList;

import static com.multi.common.JDBCConnect.*;

public class AdminService {

    private final AdminDAO adminDao;

    public AdminService() {
        adminDao = new  AdminDAO();
    }
    public boolean checkAdmin(String admin_id,String admin_pw) {
        Connection conn = getConnection();
        boolean result = adminDao.checkAdmin(conn, admin_id, admin_pw);
        close(conn);
        return result;
    }

    public ArrayList<Travel> slectAll() {
        Connection conn = getConnection();

        ArrayList<Travel> list = adminDao.selectAll(conn);
        close(conn);
        return list;
    }

    public int updateTravel(Travel travel) {
        Connection conn = getConnection();
        int result = adminDao.updateTravel(conn,travel);
        if(result>0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int deleteTravel(long no) {
        Connection conn = getConnection();
        int result = adminDao.deleteTravel(conn,no);
        if(result>0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int insertLotOfTravel() {
        Connection conn = getConnection();
        int result = adminDao.insertLotOfTravel(conn);
        if(result>0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }
}
