package com.multi.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Intro {

    private static Scanner scanner = new Scanner(System.in);
    private static GeneralView generalView = new GeneralView();
//    private static AdminView adminView = new AdminView();

    public void intro() {

        int choice = 0;

        do {
            try {
                System.out.println("\n1.일반 사용자");
                System.out.println("2. 관리자");
                System.out.println("9.프로그램 끝내기\n");
                System.out.print("번호선택 : ");

                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice){
                    case 1 -> generalView.loginOrSignup();
                    // 광호님 코드 추가시 주석 해제
                     case 2 -> new AdminView().loginView();
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
