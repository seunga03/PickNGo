package com.multi.controller;

import com.multi.model.dto.LikesDTO;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.Like;
import com.multi.service.LikeService;
import com.multi.view.DisplayView;

import java.util.ArrayList;



public class LikeController {
    private static DisplayView displayView = new DisplayView();
    private LikeService likeService = new LikeService();

    public void selectAllLikes(String userId) {
        ArrayList<TravelDTO> likedTravels = likeService.selectLikedTravelsByUserId(userId);


        if (likedTravels.isEmpty()) {
            displayView.displayError(">> 저장한 장소가 없습니다. 장소들을 조회해 저장해 보세요!");
        } else {
            displayView.displayLikedTravels(likedTravels);
        }
    }




    public void insertLike(LikesDTO like) {
        int result = likeService.insertLike(like);
        if (result > 0) {
            displayView.displayMessage("즐겨찾기에 성공적으로 등록되었습니다.");
        } else if (result == -1) {
            displayView.displayError(">> 이미 등록된 즐겨찾기입니다."); // displayError 메소드가 없다면 System.out.println() 사용
        } else {
            displayView.displayError(">> 즐겨찾기 등록에 실패했습니다. 다시 시도해주세요.");
        }
    }

}
