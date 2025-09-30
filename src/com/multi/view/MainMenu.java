package com.multi.view;

import com.multi.controller.CommentsController;
import com.multi.model.dto.CommentsDTO;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private static Scanner sc = new Scanner(System.in);
    private CommentsController commentController = new CommentsController();

    public void mainMenu() {

        int choice = 0;

        do {
            try {
                System.out.println("\n 1.전체목록 조회");
                System.out.println("2. 검색으로 조회");
                System.out.println("3. 권역별 조회");
                System.out.println("4. 인기순으로 조회");
                System.out.println("5. 마이페이지");
                System.out.println("7. 댓글등록");
                System.out.println("8. 댓글조회");
                System.out.println("9.프로그램 끝내기 \n");
                System.out.print("번호선택 : ");

                choice = sc.nextInt();  // 사용자로부터 입력 받기

                switch (choice){
                    case 7:
                        commentController.insertComment(inputComment());
                        break;
                    case 8:
                        commentController.selectAllComment(selectComment());
                        break;
                    default:
                        System.out.println("번호를 잘못입력하였습니다.");
                        break;
                }

            } catch (InputMismatchException e) {
                // 숫자가 아닌 값이 입력되었을 때 예외 처리
                System.out.println("유효한 숫자를 입력해 주세요.");
                sc.nextLine();  // 잘못된 입력을 버퍼에서 제거
            }
        } while (true);  // 무한 루프

    }

    private CommentsDTO inputComment() {

        CommentsDTO comment = new CommentsDTO();

        System.out.println("댓글을 입력하세요 >>");
        System.out.println("관광지 일련번호 : ");
        comment.setNo((long) sc.nextInt());
        System.out.println("사용자ID 입력 : ");
        comment.setUser_Id(sc.next());
        System.out.println("댓글 내용 입력 : ");
        sc.nextLine();
        comment.setContent(sc.nextLine());

        return comment;
    }

    private long selectComment() {

        System.out.println("조회할 댓글의 관광지 일련번호를 입력하세요 >>");
        System.out.println("관광지 일련번호 : ");
        return (long)sc.nextInt();

    }

    public void displaySuccess(String message) {
        System.out.println("서비스 요청결과  : " + message);
    }



}
