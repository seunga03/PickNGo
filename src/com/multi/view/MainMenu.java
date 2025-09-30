package com.multi.view;

import com.multi.controller.SearchController;
import com.multi.controller.TravelDetailController;
import com.multi.controller.CommentsController;
import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.TravelDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private SearchController searchController = new SearchController();
    private TravelDetailController travelDetailController = new TravelDetailController();
    private CommentsController commentController = new CommentsController();

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
                        searchController.selectBySearch(inputSearch());
                        break;
                    case 3:
                        System.out.println("권역별 조회");
                        searchController.selectByDistrict(inputDistrict());
                    case 7:
                        commentController.insertComment(inputComment());
                        break;
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
        return (long) sc.nextInt();

    }

    private int inputDistrict() {

        System.out.println("수도권:1, 충청권:2, 경상권:3, 전라권:4, 강원권:5, 제주권:6");
        System.out.print("조회를 원하는 권역 번호를 입력하세요: ");
        while (true) {
            try {
                int districtNo = sc.nextInt();
                if (districtNo >= 1 && districtNo <= 6) {
                    return districtNo;  // 유효한 입력인 경우 반환
                } else {
                    System.out.print("잘못된 입력입니다. 1에서 6 사이의 숫자를 입력하세요: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("유효한 숫자를 입력해 주세요: ");
                sc.next();  // 잘못된 입력을 버퍼에서 제거
            }
        }

    }

    private String inputSearch() {
        System.out.print("검색어를 입력하세요: ");
        return sc.next();  // 사용자로부터 입력 받기
    }

    public void displaySuccess(String message) {
        System.out.println("서비스 요청결과  : " + message);
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
        while (true) {
            System.out.println("\n\n");
            System.out.println("조회된 여행지 리스트는 다음과 같습니다.");

            //데이터 슬라이싱 및 표시 로직

            int startIndex = currentPage * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, dto.size());
            for (int i = startIndex; i < endIndex; i++) {

                System.out.println(dto.get(i));

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
                        System.out.println("즐겨찾기에 추가: f / 댓글 등록: c ");
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

                            case "c":
                                commentController.insertComment(inputComment());
                                break;
                            default:
                                System.out.println(">> 잘못된 명령어입니다. b, h, f, c 중에서 입력해주세요.");
                                break;

                        }


                    }


                } else {
                    System.out.println(">> 해당 번호의 여행지가 존재하지 않습니다. 목록에서 번호를 확인해주세요.");
                }
                // 상세보기가 끝나면 루프가 계속되어 현재 페이지 목록을 다시 보여줍니다


            } catch (NumberFormatException e) {

                switch (command.toLowerCase()) {
                    case "p":
                    case "P":
                        if (currentPage > 0) {
                            currentPage--;
                        } else {
                            System.out.println(">> 첫 페이지 입니다.");
                        }
                        break;
                    case "n":
                    case "N":
                        if (currentPage < totalPages - 1) {
                            currentPage++;
                        } else {
                            System.out.println(">> 마지막 페이지 입니다.");
                        }
                        break;
                    case "h":
                    case "H":
                        System.out.println(">> 메인 메뉴로 돌아갑니다.");
                        return; // 루프를 탈출하고 메소드를 종료하여 제어권을 반환
                    default:
                        System.out.println(">> 잘못된 명령어입니다. p, n, h 중에서 입력해주세요.");
                        break;
                }

            }


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

