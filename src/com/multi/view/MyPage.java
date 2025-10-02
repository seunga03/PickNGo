package com.multi.view;

import com.multi.controller.EditProfileController;
import com.multi.controller.LikeController;
import com.multi.model.dto.tmddk.User;
import com.multi.service.UserSession;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyPage {
    private static Scanner sc = new Scanner(System.in);
    private static Intro intro = new Intro();
    MainMenu mainMenu = new MainMenu();

    LikeController likeController = new LikeController();
    EditProfileView editProfileView = new EditProfileView(new EditProfileController(), this);



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


        int choice = 0;

        do {
            try {

                System.out.println();
                System.out.println("1. 내 정보 수정");
                System.out.println("2. 로그아웃");
                System.out.println("9. 메인메뉴로 돌아가기 \n");
                System.out.print("번호선택 : ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("내 정보 수정");
                        editProfileView.editProfile();
                        break;
                    case 2:
                        System.out.println("로그아웃");
                        UserSession.clear();
                        intro.intro();
                        return;
                    case 9:
                        mainMenu.mainMenu();
                        return;
                    default:
                        System.out.println("");
                }

            } catch (InputMismatchException e) {
                // 숫자가 아닌 값이 입력되었을 때 예외 처리
                System.out.println("유효한 숫자를 입력해 주세요.");
                sc.nextLine();  // 잘못된 입력을 버퍼에서 제거
            }
        } while (true);  // 무한 루프
    }
}
