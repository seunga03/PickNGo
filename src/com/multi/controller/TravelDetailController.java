package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.service.SearchService;
import com.multi.view.MainMenu;

public class TravelDetailController {
    private SearchService searchService = new SearchService();

    public TravelDTO showDetail(int no) {
        MainMenu mainMenu = new MainMenu();
        TravelDTO t = searchService.showDetail(no);

        return t;
    }
}
