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
        String admin_id = "";
        String admin_pw = "";
        do {
            displayMessage("아이디(10자 이내): ");
            admin_id = sc.next();
            displayMessage("비밀번호(30자 이내): ");
            admin_pw = sc.next();
            if (checkIdPwLength(admin_id, admin_pw)) break;
        } while (true);
        adminController.checkAdmin(admin_id, admin_pw);


    }

    private boolean checkIdPwLength(String adminId, String adminPw) {
        return adminId.length() <= 10 && adminPw.length() <= 30;
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
                displayMessage("번호를 입력해주세요.:");
                try {
                    choice = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    displayMessageLn("유효한 번호를 입력해주세요.");
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
                    displayMessageLn("유효한 숫자를 입력해주세요.");
            }

        } while (true);


    }

    private long inputNo() {
        long no = 0;
        displayMessageLn("메인 메뉴로 이동하려면 q , 작업 진행하려면 n");

        if (sc.next().equals("q")) {
            mainMenu();
            return no;
        }
        while (true) {
            System.out.print("관광지 일련번호를 입력하세요:");
            try {
                no = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                displayMessageLn("유효한 일련번호를 입력해주세요.");
                sc.next();
            }
        }
        return no;
    }

    private Travel updateTravel() {
        Travel travel = new Travel();

        displayMessageLn("메인 메뉴로 이동하려면 q , 작업 진행하려면 n");

        if (sc.next().equals("q")) {
            mainMenu();
            return null;
        }
        System.out.print("관광지 일련번호를 입력하세요.:");
        int no = sc.nextInt();
        travel.setNo(no);
        displayMessage("새로운 권역을 입력하세요.:");
        String district = sc.next();
        sc.nextLine();
        displayMessage("새로운 관광지명을 입력하세요.:");
        String title = sc.nextLine();
        displayMessage("새로운 전화 번호를 입력하세요.:");
        String phone = sc.next();
        displayMessage("새로운 주소를 입력하세요.:");
        String address = sc.next();
        sc.nextLine();
        displayMessage("새로운 관광지 설명을 입력하세요.:");
        String description = sc.nextLine();

        travel.setDistrict(district);
        travel.setTitle(title);
        travel.setPhone(phone);
        travel.setAddress(address);
        travel.setDescription(description);
        return travel;

    }


    public void displayMessageLn(String message) {
        System.out.println(message);
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayTravelList(ArrayList<Travel> list) {
        System.out.println("=====관광지 조회==================");
        for (Travel travel : list) {
            System.out.println(travel);
        }
        System.out.println();
    }
}
