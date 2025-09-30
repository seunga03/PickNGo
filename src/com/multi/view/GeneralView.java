package com.multi.view;

import com.multi.controller.GeneralController;
import com.multi.model.dto.tmddk.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralView {

    Scanner scanner = new Scanner(System.in);
    GeneralController generalController = new GeneralController();
    private static GeneralView generalView = new GeneralView();
    private static MainMenu mainMenu = new MainMenu();


    public void signUpView() {
        generalController.signUp(inputUser());
    }

    private User inputUser() {
        User u = new User();

        System.out.print("아이디: ");
        u.setUserId(scanner.nextLine().trim());

        System.out.print("이름: ");
        u.setName(scanner.nextLine().trim());

        System.out.print("비밀번호: ");
        u.setPassword(scanner.nextLine().trim());

        return u;
    }

    public void loginView() {
        System.out.print("아이디: ");
//        String userId = scanner.nextLine();
        String userId = scanner.nextLine().trim();

        System.out.print("비밀번호: ");
//        String password = scanner.nextLine();
        String password = scanner.nextLine().trim();

        User user = generalController.checkGeneral(userId, password);
        if (user != null) {
            displayMessage("로그인 성공!");
            mainMenu.mainMenu();
        } else {
            displayMessage("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }


    public void displayMessage(String s) {

        System.out.println("서비스 요청결과  : " + s);

    }
    public void loginOrSignup() {
        int choice = 0;

        do {
            try {
                System.out.println("\n1.로그인");
                System.out.println("2. 회원가입");
                System.out.println("9.프로그램 끝내기\n");
                System.out.print("번호선택 : ");

                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice){
                    case 1 -> generalView.loginView();
                    case 2 -> generalView.signUpView();
                    case 9 -> {
                        System.out.println("정말로 끝내시겠습니까? (y/n)");
                        if ('y' == scanner.next().toLowerCase().charAt(0)) {
                            return;  // 프로그램 종료
                        }
                    }
                    default -> System.out.println("번호를 잘못 입력하였습니다.");
                }

            } catch (InputMismatchException e) {
                // 예외 처리
                System.out.println("유효한 숫자를 입력해 주세요.");
                scanner.nextLine();  // 잘못된 입력을 버퍼에서 제거
            }
        } while (true);

    }
}
