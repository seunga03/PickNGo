package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.service.SearchService;
import com.multi.view.GeneralView;
import com.multi.view.MainMenu;

import java.util.ArrayList;

public class SearchController {

    private SearchService searchService = new SearchService();
    private GeneralView generalView = new GeneralView();
    public void selectBySearch(String search) {
        // Implementation for selecting by favorite
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO>list =searchService.selectBySearch(search);


       if (list.size()==0) {
              generalView.displayNoData();
         } else {
              generalView.displayTravel(list);
       }


    }

    public void selectAll() {
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO> list = searchService.selectAll();


            generalView.displayTravel(list);

    }

    public void selectByDistrict(int district_no) {
        MainMenu mainMenu = new MainMenu();
        ArrayList<TravelDTO> list = searchService.selectByDistrict(district_no);

            generalView.displayTravel(list);
    }
}
