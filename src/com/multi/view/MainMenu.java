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

                    case 1:
                        System.out.println("전체목록 조회");
                        searchController.selectAll();
                        break;

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

        final int PAGE_SIZE = 20;
        // Math.ceil을 사용하기 위해 double로 형변환하여 정확한 페이지 수 계산
        int totalPages = (int) Math.ceil((double) dto.size() / PAGE_SIZE);
        int currentPage = 0;


        if (dto == null || dto.isEmpty()) {
            System.out.println("조회된 여행지 정보가 없습니다.");
            return;
        }
        // 페이지네이션 제어 루프
        while(true){
            System.out.println("\n\n");
            System.out.println("조회된 여행지 리스트는 다음과 같습니다.");

            //데이터 슬라이싱 및 표시 로직

            int startIndex = currentPage * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, dto.size());
            for (int i = startIndex; i < endIndex; i++) {

                System.out.println(dto.get(i));

            }

            //UI 요소

            System.out.println("\n--- Page " + (currentPage + 1) + " of " + totalPages + " ---");
            System.out.print("\n[p]revious, [n]ext, [h]ome: ");

            String command = sc.next();
            switch (command.toLowerCase()) {
                case "p":
                case "P":
                    if (currentPage > 0) {
                        currentPage--;
                    } else {
                        System.out.println("첫 페이지 입니다.");
                    }
                    break;
                case "n":
                case "N":
                    if (currentPage < totalPages - 1) {
                        currentPage++;
                    } else {
                        System.out.println("마지막 페이지 입니다.");
                    }
                    break;
                case "h":
                case "H":
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return; // 루프를 탈출하고 메소드를 종료하여 제어권을 반환
                default:
                    System.out.println("잘못된 명령어입니다. p, n, h 중에서 입력해주세요.");
                    break;
            }



        }





    }

    public void displayNoData() {
    }
}