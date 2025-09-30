package com.multi.view;

import com.multi.controller.SearchController;
import com.multi.controller.TravelDetailController;
import com.multi.model.dto.TravelDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private SearchController searchController = new SearchController();
    private TravelDetailController travelDetailController = new TravelDetailController();
    private static Scanner sc = new Scanner(System.in);

    public void mainMenu() {

        int choice = 0;

        do {
            try {
                System.out.println("\n1.전체목록 조회");
                System.out.println("2. 검색으로 조회");
                System.out.println("3. 권역별 조회");
                System.out.println("4. 인기순으로 조회");
                System.out.println("5. 마이페이지");
                System.out.println("9.프로그램 끝내기 \n");
                System.out.print("번호선택 : ");

                choice = sc.nextInt();  // 사용자로부터 입력 받기

                switch (choice) {

                    case 2:
                        System.out.println("검색으로 조회");
                        searchController.selectBySearch(inputSearch());
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

    private String inputSearch() {
        System.out.print("검색어를 입력하세요: ");
        return sc.next();  // 사용자로부터 입력 받기
    }


    public void displayTravel(ArrayList<TravelDTO> dto) {
        System.out.println("조회된 여행지 리스트는 다음과 같습니다.");

        for (TravelDTO t : dto) {
            System.out.println(t);
        }

        System.out.print("여행지를 자세히 보고 싶으면 no값을 입력해 주세요: ");
        int no = sc.nextInt();
        travelDetailController.showDetail(no);

        System.out.println();
        System.out.println("1. 댓글 추가하기");
        System.out.println("2. 댓글 조회하기");
        System.out.println("5. 즐겨찾기에 추가하기");
        System.out.println("9. 조회된 여행지 리스트로 돌아가기");
        System.out.println();

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("댓글 추가하기");
                    // 댓글 컨트롤러 연결 필요
                break;
            case 2:
                System.out.println("댓글 조회하기");
                    // 댓글 컨트롤러 연결 필요
                break;
            case 5:
                System.out.println("즐겨찾기에 추가하기");
                    // 즐겨찾기 컨트롤러 연결 필요
                break;
            case 9:
                System.out.println("조회된 여행지 리스트로 돌아가기");
                    // 댓글 컨트롤러 연결 필요
                break;


        }
    }

    public void displayNoData() {
    }

    public void displayTravelDetail(TravelDTO t) {
        System.out.println("조회된 여행지는 다음과 같습니다.");
        System.out.println();

        System.out.print("(no: " + t.getNo() + ") ");
        System.out.println("[" + t.getDistrict() + "] " + t.getTitle());
        System.out.println("전화번호: " + t.getPhone());
        System.out.println("주소: " + t.getAddress());
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        printWithWrap(t.getDescription(), 92);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void printWithWrap(String text, int lineLength) {
        for (int i = 0; i < text.length(); i += lineLength) {
            int end = Math.min(i + lineLength, text.length());
            System.out.println(text.substring(i, end));
        }
    }
}