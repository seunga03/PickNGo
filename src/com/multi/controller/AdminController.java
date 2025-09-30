package com.multi.controller;

import com.multi.model.dto.adminDTO.Travel;
import com.multi.service.AdminService;
import com.multi.view.AdminView;

import java.util.ArrayList;

public class AdminController {


    AdminService adminService = new AdminService();


    public void checkAdmin(String admin_id,String admin_pw) {


        AdminView adminView = new AdminView();
        boolean result = adminService.checkAdmin(admin_id,admin_pw);

        if(result == true){
            adminView.displayMessage("로그인 성공!");
            adminView.mainMenu();
        }else{
            adminView.displayMessage("""
                    로그인 실패!
                    아이디 비밀번호를 다시 입력해주세요.
                    """);
            new AdminView().loginView();
        }

    }

    public void selectAll() {
        AdminView adminView = new AdminView();

        ArrayList<Travel> list = adminService.slectAll();
        if(list.isEmpty()){
            adminView.displayMessage("조회된 결과가 없습니다.");
        }else{
            adminView.displayTravelList(list);
        }

    }

    public void updateTravel(Travel travel) {
        AdminView adminView = new AdminView();
        int result = adminService.updateTravel(travel);
        if(result>0){
            adminView.displayMessage("업데이트 성공!");
        }else{
            adminView.displayMessage("업데이트 실패!");
        }
    }

    public void deleteTravel(long no) {
        AdminView adminView = new AdminView();
        int result = adminService.deleteTravel(no);
        if(result>0){
            adminView.displayMessage("삭제 성공!");
        }else{
            adminView.displayMessage("삭제 실패!");
        }
    }

    public void insertLotOfTravel() {

        AdminView adminView = new AdminView();
        int result = adminService.insertLotOfTravel();
        if(result>0){
            adminView.displayMessage("대량 삽입 성공!");
        }else{
            adminView.displayMessage("대량 삽입 실패!");
        }

    }
}
