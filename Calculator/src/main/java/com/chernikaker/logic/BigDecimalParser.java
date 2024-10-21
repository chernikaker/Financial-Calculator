package com.chernikaker.logic;


import java.math.BigDecimal;
import java.util.Scanner;

public class BigDecimalParser {

    boolean comma = false;
    boolean spaces = false;

    public  BigDecimal parse(String input) {

        input = input.trim();
        if(input.contains(" ")) spaces = true;
        if(input.contains(",")) comma = true;
        String cleanedInput = input.replaceAll("\\s+", "").replace(",", ".");
        return new BigDecimal(cleanedInput);
    }

    public String toString(BigDecimal num) {
        String numString = num.toString();
        int startIndex = numString.indexOf(".")-3;
        if(comma)  numString = numString.replace(".", ",");
        StringBuilder sb = new StringBuilder(numString);
        if(spaces) {
            while (startIndex > 0) {
                sb.insert(startIndex, " ");
                startIndex-=3;
            }
        }
        return sb.toString();
    }

    public boolean isComma() {
        return comma;
    }

    public boolean isSpaces() {
        return spaces;
    }
}
