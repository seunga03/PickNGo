package com.multi.view;

import com.multi.controller.LikeController;
import com.multi.model.dto.tmddk.User;
import com.multi.service.UserSession;

public class MyPage {

    LikeController likeController = new LikeController();


    public void myInfo() {
        User me = UserSession.getUser();
        System.out.println("===============마이페이지===============");
        System.out.println("유형: " + me.getRole());
        System.out.println("아이디: " + me.getUserId());
        System.out.println("비밀번호: " + me.getPassword());
        System.out.println("이름  : " + me.getName());
        System.out.println("마지막 수정: " + me.getUpdatedAt());
        System.out.println("======================================");
        System.out.println("즐겨찾기");
        likeController.selectAllLikes(me.getUserId());


    }
}
