package com.example.luckynumber;

public class characterToNumber {
    public static int getAlphabetIndex(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        } else if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 1;
        } else {
            return -1;
        }
    }
}
