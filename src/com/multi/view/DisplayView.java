package com.multi.view;

import com.multi.controller.CommentsController;
import com.multi.controller.LikeController;
import com.multi.controller.TravelDetailController;
import com.multi.model.dto.CommentsDTO;
import com.multi.model.dto.LikesDTO;
import com.multi.model.dto.TravelDTO;
import com.multi.model.dto.tmddk.User;
import com.multi.service.UserSession;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayView {
    Scanner sc = new Scanner(System.in);
    private TravelDetailController travelDetailController = new TravelDetailController();
    private LikeController likeController = new LikeController();
    private CommentsController commentController = new CommentsController();



    public void displayMessage(String s) {

        System.out.println("서비스 요청결과  : " + s);

    }
    public void displayError(String message) {
        System.out.println( message);
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
                        System.out.println("즐겨찾기에 추가: f / 댓글 등록: c  / 댓글 조회 v");
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

    public void displayLikedTravels(ArrayList<TravelDTO> list) {

        for (int i = 0; i < list.size(); i++) {
            TravelDTO travel = list.get(i);
            // 예시: [1] 서울의 궁궐 나들이 (수도권)
            System.out.println("  " + (i + 1) + ". " + travel.getTitle() + " (" + travel.getDistrict() + ")");
        }
    }
}
