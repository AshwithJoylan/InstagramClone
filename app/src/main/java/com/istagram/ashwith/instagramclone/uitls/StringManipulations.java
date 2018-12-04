package com.istagram.ashwith.instagramclone.uitls;

public class StringManipulations {

    public static String expandUseerNAme(String userName) {
        return userName.replace(".", " ");
    }

    public static String condenceUSerName(String userName) {
        return userName.replace(" ", "+");
    }
}
