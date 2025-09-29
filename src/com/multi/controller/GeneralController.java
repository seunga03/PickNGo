package com.multi.controller;

import com.multi.model.dto.tmddk.User;
import com.multi.service.GeneralService;
import com.multi.view.GeneralView;

public class GeneralController {

    private GeneralService generalService = new GeneralService();

    public boolean checkGeneral(String userId, String password) {
        return generalService.checkGeneral(userId, password);
    }

    public void signUp(User user) {
        GeneralView generalView = new GeneralView();

        int result = generalService.signUp(user);

        if (result > 0) {
            generalView.displayMessage("회원가입성공");
        }
    }
}
