package com.example.web.util;

import java.util.Random;

public class RandomUntil {
    public static char[] randomFull() {
        char[] randomCode = new char[8];
        String upper = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghiklmnopqrstuvwxyz";
        String num = "0123456789";
        String combination = num+upper+lower;
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            randomCode[i] = combination.charAt(r.nextInt(combination.length()));
        }
        return randomCode ;

    }
    public static char[] randomNumber() {
        char[] randomCode = new char[6];
        String num = "0123456789";
        String combination = num;
        Random r = new Random();

        for (int i = 0; i < 6; i++) {
            randomCode[i] = combination.charAt(r.nextInt(combination.length()));
        }
        return randomCode ;

    }
}
