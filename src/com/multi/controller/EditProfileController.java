package com.multi.controller;

import com.multi.model.dao.UserDAO;
import com.multi.model.dto.tmddk.User;
import com.multi.service.EditProfileService;
import com.multi.service.UserSession;
import com.multi.view.MainMenu;

public class EditProfileController {

    private final EditProfileService editProfileService = new EditProfileService();


    public void editName(String newName) {
        int result = editProfileService.editName(newName);
        if(result > 0) {
            new MainMenu().displaySuccess("이름 수정 성공");
        }
    }

    public void editPassword(String newPassword) {
        int result = editProfileService.editPassword(newPassword);
        if(result > 0) {
            new MainMenu().displaySuccess("비밀번호 재설정 성공");
        }
    }
}
