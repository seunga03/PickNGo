package com.multi.view;

import com.multi.controller.GeneralController;
import com.multi.controller.TravelDetailController;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralView {

    Scanner scanner = new Scanner(System.in);
    GeneralController generalController = new GeneralController();
    private static GeneralView generalView = new GeneralView();
    private static MainMenu mainMenu = new MainMenu();
    private TravelDetailController travelDetailController = new TravelDetailController();
    public void signUpView() {
        generalController.signUp(inputUser());
    }
    private static Scanner sc = new Scanner(System.in);
    private User inputUser() {

        String user_id = "";
        String user_name = "";
        String user_pw = "";
        do {
            System.out.print("아이디(10자 이내): ");
            user_id = scanner.nextLine().trim();
            System.out.print("이름(30자 이내): ");
            user_name = scanner.nextLine().trim();
            System.out.print("비밀번호(30자 이내): ");
            user_pw = scanner.next().trim();
            if (checkIdPwLength(user_id,user_name,user_pw)){
                break;
            }
            System.out.println("==아이디,이름,비번 형식 오류. 다시 입력해주세요");
        } while (true);

        User u = new User();
        u.setUserId(user_id);
        u.setName(user_name);
        u.setPassword(user_pw);


        return u;
    }

    private boolean checkIdPwLength(String user_id, String user_name, String user_pw) {
        return user_id.length() <= 10 && user_name.length() <= 30 && user_pw.length() <= 30;
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
    public void displayError(String message) {

        System.out.println( message);
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

                switch (choice) {
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
                        if (currentPage > 0) {
                            currentPage--;
                        } else {
                            System.out.println(">> 첫 페이지 입니다.");
                        }
                        break;
                    case "n":
                        if (currentPage < totalPages - 1) {
                            currentPage++;
                        } else {
                            System.out.println(">> 마지막 페이지 입니다.");
                        }
                        break;
                    case "h":
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

    public static void displayTravelDetail(TravelDTO t) {
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
