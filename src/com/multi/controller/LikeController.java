package com.multi.controller;

import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.LikesDTO;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.Like;
import com.multi.service.LikeService;
import com.multi.view.DisplayView;
import com.multi.view.GeneralView;
import com.multi.view.MainMenu;

import java.util.ArrayList;



public class LikeController {
    private static DisplayView displayView = new DisplayView();

    private LikeService likeService = new LikeService();
    private GeneralView generalView = new GeneralView();
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

    public void insertLike(LikesDTO like) {
        int result = likeService.insertLike(like);
        if (result > 0) {
            displayView.displayMessage(">> 즐겨찾기에 성공적으로 등록되었습니다.");
        } else if (result == -1) {
            displayView.displayError(">> 이미 등록된 즐겨찾기입니다."); // displayError 메소드가 없다면 System.out.println() 사용
        } else {
            displayView.displayError(">> 즐겨찾기 등록에 실패했습니다. 다시 시도해주세요.");
        }
    }

}
