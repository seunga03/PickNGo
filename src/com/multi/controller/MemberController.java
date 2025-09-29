package com.multi.controller;

import com.multi.model.dto.Member;
import com.multi.service.MemberService;
import com.multi.view.MainMenu;

import java.util.ArrayList;

public class MemberController {


    private static MemberService memberService = new MemberService();


    public static void selectAll() {
        MainMenu memberMenu = new MainMenu();

        ArrayList<Member> list = memberService.selectAll();

        if (!list.isEmpty()) {
            memberMenu.displayMemberList(list);
        } else {
            memberMenu.displayNoData();
        }
    }

    public static void selectOne(String s) {
        MainMenu memberMenu = new MainMenu();

        Member m = memberService.selectOne(s);

        if (m != null) {
            memberMenu.displayMember(m);
        } else {
            memberMenu.displayNoData();
        }
    }

    public static void selectByName(String s) {
        MainMenu memberMenu = new MainMenu();

        Member m = memberService.selectByName(s);

        if (m != null) {
            memberMenu.displayMember(m);
        } else {
            memberMenu.displayNoData();
        }
    }

    public static void insertMember(Member member) {
        int result = memberService.insertMember(member);

        if (result > 0) {
            new MainMenu().displaySuccess("회원가입성공");
        }
    }

    public static void updateMember(Member member) {
        int result = memberService.updateMember(member);

        if (result > 0) {
            new MainMenu().displaySuccess("회원수정성공");
        }
    }

    public static void deleteMember(String s) {
        int result = memberService.deleteMember(s);

        if (result > 0) {
            new MainMenu().displaySuccess("회원삭제성공");
        }
    }

    public static void selectAllDeleteMember() {
        MainMenu memberMenu = new MainMenu();

        ArrayList<Member> list = memberService.selectAllDeleteMember();

        if (!list.isEmpty()) {
            memberMenu.displayMemberList(list);
        } else {
            memberMenu.displayNoData();
        }
    }
}
