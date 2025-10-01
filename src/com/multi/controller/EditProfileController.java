package com.multi.controller;

import com.multi.model.dao.UserDAO;
import com.multi.model.dto.tmddk.User;
import com.multi.service.EditProfileService;
import com.multi.service.UserSession;
import com.multi.view.DisplayView;
import com.multi.view.MainMenu;

public class EditProfileController {

    private static DisplayView displayView = new DisplayView();

    private final EditProfileService editProfileService = new EditProfileService();

    public void editName(String newName) {
        User fresh = editProfileService.editNameAndRefresh(newName);
        if (fresh != null) {
            displayView.displayMessage("이름 수정 성공! (현재 이름: " + fresh.getName() + ")");
        } else {
            displayView.displayMessage("이름 수정 실패");
        }
    }

    public void editPassword(String newPassword) {
        User fresh = editProfileService.editNameAndRefresh(newPassword);
        if (fresh != null) {
            displayView.displayMessage("비밀번호 수정 성공! (현재 비밀번호: " + fresh.getPassword() + ")");
        } else {
            displayView.displayMessage("비밀번호 수정 실패");
        }
    }
}
