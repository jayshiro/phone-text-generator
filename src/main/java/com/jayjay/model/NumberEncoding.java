package com.jayjay.model;

import java.util.Arrays;
import java.util.Optional;

public enum NumberEncoding {
    TWO(2, new String [] {"A","B","C"}),
    THREE(3, new String [] {"D","E","F"}),
    FOUR(4, new String [] {"G","H","I"}),
    FIVE(5, new String [] {"J","K","L"}),
    SIX(6, new String [] {"M","N","O"}),
    SEVEN(7, new String [] {"P","Q","R","S"}),
    EIGHT(8, new String [] {"T","U","V"}),
    NINE(9, new String [] {"W","X","Y","Z"})
    ;

    private int digit;
    private String [] chars;
    NumberEncoding(int digit, String [] chars) {
        this.digit = digit;
        this.chars = chars;
    }

    public int getDigit() {
        return digit;
    }

    public String[] getChars() {
        return chars;
    }

    public boolean contains(String str) {
        return Arrays.toString(chars).contains(str);
    }

    public static Optional<NumberEncoding> findByDigit(final int digit) {
        return Arrays.asList(values())
                .stream()
                .filter(numberEncoding -> {
                    if(numberEncoding.getDigit() == digit) {
                        return true;
                    }
                    return false;
                })
                .findFirst();
    }
}
