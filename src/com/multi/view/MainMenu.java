package com.multi.view;

import com.multi.controller.LikeController;
import com.multi.controller.SearchController;
import com.multi.controller.TravelDetailController;
import com.multi.controller.CommentsController;
import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.LikesDTO;
import com.multi.model.dto.TravelDTO;

import com.multi.view.InputView.*;

import com.multi.model.dto.tmddk.User;
import com.multi.service.UserSession;


import java.util.ArrayList;
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

                    case 7:
                        commentController.insertComment(inputComment());
                        break;

                        searchController.selectByDistrict(inputDistrict());
                  //  case 7:
                  //      commentController.insertComment(inputComment());
                  //      break;

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
                            return;  // 프로그램 종료

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

        System.out.println("조회할 댓글의 관광지 일련번호를 입력하세요 >>");
        System.out.println("관광지 일련번호 : ");
        return (long) sc.nextInt();

    }


    public void displaySuccess(String message) {
        System.out.println("서비스 요청결과  : " + message);
    }





            }


            //UI 요소

//            int choice = sc.nextInt();
//            switch (choice) {
//                case 1:
//                    System.out.println("댓글 추가하기");
//                    commentController.insertComment(inputComment());
//                    break;    // 댓글 컨트롤러 연결 필요
//                case 2:
//                    System.out.println("댓글 조회하기");
//                    commentController.selectAllComment(selectComment());
//                    // 댓글 컨트롤러 연결 필요
//                    break;
//                case 5:
//                    System.out.println("즐겨찾기에 추가하기");
//                    // 즐겨찾기 컨트롤러 연결 필요
//                    break;
//                case 9:
//                    System.out.println("조회된 여행지 리스트로 돌아가기");
//                    // 댓글 컨트롤러 연결 필요
//                    break;
//
//            }
            System.out.println("\n--- Page " + (currentPage + 1) + " of " + totalPages + " ---");
            System.out.print("\n[p]revious, [n]ext, [h]ome | 여행지 상세정보는 번호 입력: ");

            String command = sc.next();

            try {
                int travelNo = Integer.parseInt(command);

                // 2. 숫자로 변환 성공 시, 상세 정보 조회 로직 실행
                TravelDTO selectedTravel = travelDetailController.showDetail(travelNo);

                if (selectedTravel != null) {
                    // 상세 정보를 출력하는 메소드 호출
                    displayTravelDetail(selectedTravel);


                    boolean inDetailView = true;
                    while (inDetailView) {
                        System.out.println("\n");
                        System.out.println("목록으로 돌아가기: b / 메인으로 돌아가기: h ");
                        System.out.println("즐겨찾기에 추가: f / 댓글 등록: c / 댓글 조회 v");
                        System.out.print("명령어 입력: ");
                        String detailCommand = sc.next();

                        switch (detailCommand.toLowerCase()) {
                            case "b":
                                inDetailView = false; // 상세보기 루프 종료
                                break;
                            case "h":
                                System.out.println(">> 메인 메뉴로 돌아갑니다.");
                                return;

                            case "f":
                                likeController.insertLike(insertLike(travelNo));
                                break;
                            case "c":
                                commentController.insertComment(inputComment(travelNo));
                                break;
                            case "v":
                                commentController.selectAllComment((long)travelNo);
                                break;
                            default:
                                System.out.println(">> 잘못된 명령어입니다. b, h, f, c 중에서 입력해주세요.");
                                break;

                        }



}

