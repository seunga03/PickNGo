package com.multi.controller;

import com.multi.model.dto.tmddk.User;
import com.multi.service.GeneralService;
import com.multi.view.DisplayView;

public class GeneralController {
    private static DisplayView displayView = new DisplayView();

    private GeneralService generalService = new GeneralService();

    public User checkGeneral(String userId, String password) {
        return generalService.checkGeneral(userId, password);
    }

    public void signUp(User user) {

        int result = generalService.signUp(user);

        if (result > 0) {
            displayView.displayMessage("회원가입성공");
        }
    }
}
