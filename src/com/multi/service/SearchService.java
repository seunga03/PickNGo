package com.multi.service;

import com.multi.model.dao.TravelDAO;
import com.multi.model.dto.TravelDTO;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.ArrayList;

import static com.multi.common.JDBCConnect.*;

public class SearchService {


    private final TravelDAO travelDAO;
    public SearchService() {travelDAO = new TravelDAO();}


    public ArrayList<TravelDTO> selectBySearch(String search) {

        Connection conn = getConnection();
        ArrayList<TravelDTO> list = travelDAO.selectBySearch(conn, search);
        close(conn);

        return list;

    }

    public TravelDTO showDetail(int no) {

        Connection conn = getConnection();
        TravelDTO t = travelDAO.showDetail(conn, no);
        close(conn);

        return t;
    }

}
