package com.multi.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public int inputDistrict() {

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

    public String inputSearch() {
        System.out.print("검색어를 입력하세요: ");
        return sc.next();  // 사용자로부터 입력 받기
    }

}
