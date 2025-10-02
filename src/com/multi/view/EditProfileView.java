package com.multi.view;

import com.multi.controller.EditProfileController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EditProfileView {
    private final EditProfileController editProfileController;
    private final MyPage myPage;

    public EditProfileView(EditProfileController editProfileController, MyPage myPage) {
        this.editProfileController = editProfileController;
        this.myPage = myPage;
    }

    Scanner scanner = new Scanner(System.in);

    public void editProfile() {
        int choice = 0;

        do {
            try {
                System.out.println();
                System.out.println("1. 이름 수정");
                System.out.println("2. 비밀번호 재설정");
                System.out.println("9. 돌아가기\n");
                System.out.print("번호선택 : ");

                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice){
                    case 1 -> {
                        System.out.print("새로 설정할 이름을 입력해주세요: ");
                        String newName = scanner.nextLine().trim();
                        editProfileController.editName(newName);
                    }
                    case 2 -> {
                        System.out.print("새로 설정할 비밀번호를 입력해주세요: ");
                        String newPassword = scanner.nextLine().trim();
                        editProfileController.editPassword(newPassword);
                    }
                    case 9 -> {
                        System.out.println("마이페이지로 돌아갑니다.");
                        return;
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
