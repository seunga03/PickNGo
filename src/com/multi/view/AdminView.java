package com.multi.view;

import com.multi.controller.AdminController;
import com.multi.model.dto.adminDTO.Travel;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
            System.out.println();
            System.out.println("""
                    =관리자 메인메뉴=======
                    1.관광지 조회
                    2.csv 파일에 추가된 관광지들 대량 등록 (resources에 travel.csv 업로드해주세요.)
                    3.관광지 수정
                    4.관광지 삭제
                    9.프로그램 종료
                    ====================
                    """);

            while (true) {
                displayMessage("번호를 입력해주세요.");
                try {
                    choice = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    displayMessage("유효한 번호를 입력해주세요.");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    adminController.selectAll();
                    break;
                case 2:
                    adminController.insertLotOfTravel();
                    break;
                case 3:
                    adminController.updateTravel(updateTravel());
                    break;
                case 4:
                    adminController.deleteTravel(inputNo());
                    break;
                case 9:
                    return;
                default:
                    displayMessage("유효한 숫자를 입력해주세요.");
            }

        } while (true);


    }

    private long inputNo() {
        long no = 0;
        while (true) {
            System.out.print("관광지 일련번호를 입력하세요:");
            try {
                no  = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                displayMessage("유효한 일련번호를 입력해주세요.");
                sc.next();
            }
        }
        return no;
    }

    private Travel updateTravel() {
        Travel travel = new Travel();

        travel.setNo(inputNo());
        System.out.print("새로운 권역을 입력하세요.:");
        String district = sc.next();
        sc.nextLine();
        System.out.print("새로운 관광지명을 입력하세요.:");
        String title = sc.nextLine();
        System.out.print("새로운 전화 번호를 입력하세요.:");
        String phone = sc.next();
        System.out.print("새로운 주소를 입력하세요.:");
        String address = sc.next();
        sc.nextLine();
        System.out.print("새로운 관광지 설명을 입력하세요.:");
        String description = sc.nextLine();

        travel.setDistrict(district);
        travel.setTitle(title);
        travel.setPhone(phone);
        travel.setAddress(address);
        travel.setDescription(description);
        return travel;

    }


    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayTravelList(ArrayList<Travel> list) {
        System.out.println("=====관광지 조회==================");
        for (Travel travel : list) {
            System.out.println(travel);
        }
        System.out.println();
    }
}
