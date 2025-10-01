package com.multi.run;

import com.multi.view.Intro;
import com.multi.view.MainMenu;

public class App {
    public static void main(String[] args) {
        System.out.println( "프로젝트 통합 Version 1.0");
        new Intro().intro();
//        new MainMenu().mainMenu();
    }
}
