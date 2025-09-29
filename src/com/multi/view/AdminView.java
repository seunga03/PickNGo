package com.multi.view;

import com.multi.controller.AdminController;
import com.multi.model.dto.adminDTO.Travel;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminView {

    Scanner sc = new Scanner(System.in);
    AdminController adminController = new AdminController();

    public void loginView() {


        System.out.print("아이디: ");
        String admin_id = sc.next();
        System.out.print("비밀번호: ");
        String admin_pw = sc.next();
        adminController.checkAdmin(admin_id, admin_pw);


    }

    public void mainMenu() {   //관리자 메인 메뉴
        int choice = 0;
        do {
            System.out.println("""
                    =관리자 메인메뉴=======
                    1.관광지 조회
                    2.csv 파일에 추가된 관광지들 대량 등록
                    3.관광지 수정
                    4.관광지 삭제
                    9.프로그램 종료
                    ====================
                    """);
            choice = sc.nextInt();
            System.out.println(choice);
            switch (choice) {
                case 1:
                    adminController.selectAll();
                    break;
                case 2:
//                    adminController.insertLotOfTravel();
                    break;
                case 3:
//                    adminController.updateTravel();
                    break;
                case 4:
//                    adminController.deleteTravel();
                    break;
                case 9:
                    return;
            }

        } while (true);


    }


    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayTravelList(ArrayList<Travel> list) {
        System.out.println("==============================");
        for (Travel travel : list) {
            System.out.println(travel);
        }
        System.out.println();
    }
}
