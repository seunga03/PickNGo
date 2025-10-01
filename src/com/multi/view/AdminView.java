package com.multi.view;

import com.multi.controller.AdminController;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.adminDTO.Travel;
import com.multi.model.dto.dto;
import com.multi.service.SearchService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.multi.view.GeneralView.displayTravelDetail;


public class AdminView {

    Scanner sc = new Scanner(System.in);
    AdminController adminController = new AdminController();

    public void loginView() {
        String admin_id = "";
        String admin_pw = "";
        do {
            displayMessage("아이디(10자 이내): ");
            admin_id = sc.next();
            displayMessage("비밀번호(30자 이내): ");
            admin_pw = sc.next();
            if (checkIdPwLength(admin_id, admin_pw)) break;
        } while (true);
        adminController.checkAdmin(admin_id, admin_pw);


    }

    private boolean checkIdPwLength(String adminId, String adminPw) {
        return adminId.length() <= 10 && adminPw.length() <= 30;
    }

    public void mainMenu() {   //관리자 메인 메뉴
        int choice = 0;

        do {
            System.out.println();
            System.out.println("""
                    =======관리자 메인메뉴=======
                    1.관광지 조회
                    2.csv 파일에 추가된 관광지들 대량 등록
                    (resources에 travel.csv 업로드해주세요.)
                    3.관광지 수정
                    4.관광지 삭제
                    9.프로그램 종료
                    ============================
                    """);

            while (true) {
                displayMessage("번호를 입력해주세요.:");
                try {
                    choice = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    displayMessageLn("유효한 번호를 입력해주세요.");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    adminController.selectAll();
                    break;
                case 2:
                    adminController.insertLotOfTravel();
                    break;
                case 3:
                    adminController.updateTravel(updateTravel());
                    break;
                case 4:
                    adminController.deleteTravel(inputNo());
                    break;
                case 9:
                    return;
                default:
                    displayMessageLn("유효한 숫자를 입력해주세요.");
            }

        } while (true);


    }

    private long inputNo() {
        long no = 0;
        displayMessageLn("메인 메뉴로 이동하려면 q , 작업 진행하려면 n");

        if (sc.next().equals("q")) {
            mainMenu();
            return no;
        }
        while (true) {
            System.out.print("관광지 일련번호를 입력하세요:");
            try {
                no = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                displayMessageLn("유효한 일련번호를 입력해주세요.");
                sc.next();
            }
        }
        return no;
    }

    private Travel updateTravel() {
        Travel travel = new Travel();

        displayMessageLn("메인 메뉴로 이동하려면 q , 작업 진행하려면 n");

        if (sc.next().equals("q")) {
            mainMenu();
            return null;
        }
        System.out.print("관광지 일련번호를 입력하세요.:");
        int no = sc.nextInt();
        travel.setNo(no);
        displayMessage("새로운 권역을 입력하세요.:");
        String district = sc.next();
        sc.nextLine();
        displayMessage("새로운 관광지명을 입력하세요.:");
        String title = sc.nextLine();
        displayMessage("새로운 전화 번호를 입력하세요.:");
        String phone = sc.next();
        displayMessage("새로운 주소를 입력하세요.:");
        String address = sc.next();
        sc.nextLine();
        displayMessage("새로운 관광지 설명을 입력하세요.:");
        String description = sc.nextLine();

        travel.setDistrict(district);
        travel.setTitle(title);
        travel.setPhone(phone);
        travel.setAddress(address);
        travel.setDescription(description);
        return travel;

    }


    public void displayMessageLn(String message) {
        System.out.println(message);
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayTravelList(ArrayList<Travel> list) {
        final int PAGE_SIZE = 20;
        // Math.ceil을 사용하기 위해 double로 형변환하여 정확한 페이지 수 계산
        int totalPages = (int) Math.ceil((double) list.size() / PAGE_SIZE);
        int currentPage = 0;


        if (list == null || list.isEmpty()) {
            System.out.println("조회된 여행지 정보가 없습니다.");
            return;
        }
        // 페이지네이션 제어 루프
        while (true) {
            System.out.println("\n\n");
            System.out.println("조회된 여행지 리스트는 다음과 같습니다.");

            //데이터 슬라이싱 및 표시 로직

            int startIndex = currentPage * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, list.size());
            for (int i = startIndex; i < endIndex; i++) {

                System.out.println(list.get(i));

            }


            //UI 요소

            System.out.println("\n--- Page " + (currentPage + 1) + " of " + totalPages + " ---");
            System.out.print("\n[p]revious, [n]ext, [h]ome | 여행지 상세정보는 번호 입력: ");

            String command = sc.next();

            try {
                int travelNo = Integer.parseInt(command);

                // 2. 숫자로 변환 성공 시, 상세 정보 조회 로직 실행

                SearchService travelDetailController = new SearchService();
                TravelDTO selectedTravel = travelDetailController.showDetail(travelNo);

                if (selectedTravel != null) {
                    // 상세 정보를 출력하는 메소드 호출
                    displayTravelDetail(selectedTravel);


                    boolean inDetailView = true;
                    while (inDetailView) {
                        System.out.println("\n");
                        System.out.println("이전으로 돌아가기: b , 메인으로 돌아가기: h ");

                        System.out.print("명령어 입력: ");
                        String detailCommand = sc.next();

                        switch (detailCommand.toLowerCase()) {
                            case "b":
                                inDetailView = false; // 상세보기 루프 종료
                                break;
                            case "h":
                                System.out.println(">> 메인 메뉴로 돌아갑니다.");
                                return;

                            default:
                                System.out.println(">> 잘못된 명령어입니다. b, h 중에서 입력해주세요.");
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
}
