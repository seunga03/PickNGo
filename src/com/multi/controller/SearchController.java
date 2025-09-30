package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.service.SearchService;
import com.multi.view.MainMenu;

import java.util.ArrayList;

public class SearchController {

    private SearchService searchService = new SearchService();

    public void selectBySearch(String search) {
        // Implementation for selecting by favorite
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO>list =searchService.selectBySearch(search);


       if (list.size()==0) {
              mainMenu.displayNoData();
         } else {
              mainMenu.displayTravel(list);
       }


    }
}
