package com.multi.view;

import com.multi.controller.LikeController;
import com.multi.controller.SearchController;
import com.multi.controller.TravelDetailController;
import com.multi.controller.CommentsController;
import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.LikesDTO;
import com.multi.model.dto.tmddk.User;
import com.multi.service.UserSession;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private SearchController searchController = new SearchController();

    private InputView inputView = new InputView();
    private TravelDetailController travelDetailController = new TravelDetailController();

    private CommentsController commentController = new CommentsController();
    private LikeController likeController = new LikeController();


    private static MyPage myPage = new MyPage();

    private static Scanner sc = new Scanner(System.in);

    public void mainMenu() {

        int choice = 0;

        do {
            try {
                System.out.println("\n1. 전체목록 조회");
                System.out.println("2. 검색으로 조회");
                System.out.println("3. 권역별 조회");
                System.out.println("4. 인기순으로 조회");
                System.out.println("5. 마이페이지");
                // System.out.println("7. 댓글등록");
                System.out.println("8. 댓글조회");
                System.out.println("9.프로그램 끝내기 \n");
                System.out.print("번호선택 : ");

                choice = sc.nextInt();  // 사용자로부터 입력 받기
                sc.nextLine();
                switch (choice) {


                    case 1:
                        System.out.println("전체목록 조회");
                        searchController.selectAll();
                        break;

                    case 2:
                        System.out.println("검색으로 조회");
                        searchController.selectBySearch(inputView.inputSearch());
                        break;
                    case 3:
                        System.out.println("권역별 조회");

                        searchController.selectByDistrict(inputView.inputDistrict());


                    case 8:
                        commentController.selectAllComment(selectComment());
                        break;
                    case 5:
                        System.out.println("마이페이지로");
                        myPage.myInfo();
                        break;
                    case 9:
                        System.out.println("정말로 끝내시겠습니까??(y/n)");
                        if ('y' == sc.next().toLowerCase().charAt(0)) {
                            System.exit(0);  // 프로그램 종료

                        }
                }

            } catch (InputMismatchException e) {
                // 숫자가 아닌 값이 입력되었을 때 예외 처리
                System.out.println("유효한 숫자를 입력해 주세요.");
                sc.nextLine();  // 잘못된 입력을 버퍼에서 제거
            }
        } while (true);  // 무한 루프

    }

    private CommentsDTO inputComment(int no) {

        CommentsDTO comment = new CommentsDTO();

        System.out.println("댓글을 입력하세요 >>");
        System.out.println("관광지 일련번호 : " + no + "입니다");
        //  comment.setNo((long) sc.nextInt());
        comment.setNo((long) no);
        User me = UserSession.getUser();
        System.out.println("사용자ID 입력 : " + me.getUserId());

        //comment.setUser_Id(sc.next());
        comment.setUser_Id(me.getUserId());
        System.out.println("댓글 내용 입력 : ");
        sc.nextLine();
        comment.setContent(sc.nextLine());

        return comment;
    }

    private LikesDTO insertLike(int no) {

        LikesDTO like = new LikesDTO();

        System.out.println("즐겨찾기 관광지 일련번호 : " + no + "입니다");

        like.setNo((long) no);
        User me = UserSession.getUser();
        System.out.println("사용자ID 입력 : " + me.getUserId());

        like.setUser_Id(me.getUserId());

        return like;
    }

    private long selectComment() {

        System.out.print("조회할 댓글의 관광지 일련번호를 입력하세요 : ");
        return (long) sc.nextInt();

    }



}