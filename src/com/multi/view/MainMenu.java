package com.multi.view;

import com.multi.controller.SearchController;
import com.multi.model.dto.TravelDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private SearchController searchController = new SearchController();
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

    }

    public void displayNoData() {
    }
}