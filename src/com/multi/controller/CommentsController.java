package com.multi.controller;

import com.multi.model.dto.CommentsDTO;
import com.multi.service.CommentsService;
import com.multi.view.DisplayView;
import com.multi.view.GeneralView;
import com.multi.view.MainMenu;

import java.awt.*;
import java.util.ArrayList;

public class CommentsController {
    private static DisplayView displayView = new DisplayView();

    private CommentsService commentService = new CommentsService();
    private GeneralView generalView = new GeneralView();
    public void insertComment(CommentsDTO comment) {
        int result = commentService.insertComment(comment);
        if(result > 0) {
            displayView.displayMessage("댓글등록성공");
        }
    }

    public void selectAllComment(Long no) {

        ArrayList<CommentsDTO> list =  commentService.selectAllComment(no);

        if(!list.isEmpty()) {
            System.out.println("\n조회된 댓글은 다음과 같습니다.");
            for (CommentsDTO comment : list)  {
                System.out.println(comment);
            }
        } else {
            System.out.println("댓글이 없습니다.");
        }
    }

}
