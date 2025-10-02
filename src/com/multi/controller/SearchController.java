package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.service.SearchService;
import com.multi.view.DisplayView;
import com.multi.view.GeneralView;
import com.multi.view.MainMenu;

import java.util.ArrayList;

public class SearchController {
    private static DisplayView displayView = new DisplayView();


    private SearchService searchService = new SearchService();
    public void selectBySearch(String search) {
        // Implementation for selecting by favorite
        ArrayList<TravelDTO>list =searchService.selectBySearch(search);

       if (list.size()==0) {
              displayView.displayNoData();
         } else {
              displayView.displayTravel(list);
       }

    }

    public void selectAll() {
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO> list = searchService.selectAll();


            displayView.displayTravel(list);

    }

    public void selectByDistrict(int district_no) {
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO> list = searchService.selectByDistrict(district_no);

            displayView.displayTravel(list);
    }

    public void selectByLikes() {
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO> list = searchService.selectByLikes();

            displayView.displayTravel(list);

    }
}
