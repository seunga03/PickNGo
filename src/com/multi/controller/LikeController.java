package com.multi.controller;

import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.Like;
import com.multi.service.LikeService;

import java.util.ArrayList;

public class LikeController {
    private LikeService likeService = new LikeService();
    public void selectAllLikes(String userId) {
        ArrayList<Like> list =likeService.selectAllLikes(userId);


        if (list.size()==0) {
            displayNoData();
        } else {
            displayLike(list);
        }
    }

    private void displayLike(ArrayList<Like> list) {
        for (Like like : list) {
            System.out.println(like);
        }
    }

    private void displayNoData() {
        System.out.println();
        System.out.println("아직 저장한 장소가 없습니다. 장소들을 조회해 저장해 보세요!");
        System.out.println();
    }
}
