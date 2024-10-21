package com.chernikaker.logic;


import java.math.BigDecimal;
import java.util.Scanner;

public class BigDecimalParser {

    boolean comma = false;
    boolean spaces = false;
    private static BigDecimal MAX_VALUE = new BigDecimal("1000000000000.000000");
    private static BigDecimal MIN_VALUE = new BigDecimal("1000000000000.000000");

    public  BigDecimal parse(String input) {

        input = input.trim();
        String regex = "^[0-9\\s.,-]*$";

        if(! input.matches(regex)) throw new IllegalArgumentException("Invalid input");
        if(input.contains(" ")) spaces = true;
        if(input.contains(",")) comma = true;
        String cleanedInput = input.replaceAll("\\s+", "").replace(",", ".");
        cleanedInput = cleanedInput.replaceAll("(\\.\\d*[^0])0+$", "$1");
        BigDecimal d = new BigDecimal(cleanedInput);

        return new BigDecimal(cleanedInput);
    }

    public String toString(BigDecimal num) {
        String numString = num.toPlainString();
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
