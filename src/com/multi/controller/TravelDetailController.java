package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.service.SearchService;
import com.multi.view.MainMenu;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class TravelDetailController {
    private SearchService searchService = new SearchService();

    public void showDetail(int no) {
        MainMenu mainMenu = new MainMenu();
        TravelDTO t = searchService.showDetail(no);

        if (t != null) {
            mainMenu.displayTravelDetail(t);
        } else {
            mainMenu.displayNoData();
        }
    }
}
