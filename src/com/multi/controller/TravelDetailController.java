package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.service.SearchService;

public class TravelDetailController {
    private SearchService searchService = new SearchService();

    public TravelDTO showDetail(int no) {
        TravelDTO t = searchService.showDetail(no);

        return t;
    }
}
