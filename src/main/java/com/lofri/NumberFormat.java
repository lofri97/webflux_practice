package com.lofri;

public class NumberFormat {
    private int digit;
    private String word;

    public NumberFormat(int digit, String word) {
        this.digit = digit;
        this.word = word;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + digit +
                ", name='" + word + '\'' +
                '}';
    }
}
