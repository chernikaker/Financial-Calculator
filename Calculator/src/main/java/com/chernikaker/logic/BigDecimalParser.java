package com.chernikaker.logic;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class BigDecimalParser {


    public  BigDecimal parse(String input) {

        input = input.trim();
        String regex = "^[0-9\\s.,-]*$";
        if(! input.matches(regex)) throw new IllegalArgumentException("Invalid characters in input");
        if(input.indexOf('-') != -1 && input.indexOf('-') != 0) throw new IllegalArgumentException("Invalid input: - character can be only before the number");
        String cleanedInput = input.replace(",", ".");
        if(input.contains(" ")) checkSpacedInput(cleanedInput);
        cleanedInput = cleanedInput.replaceAll(" ","");

        return new BigDecimal(cleanedInput);
    }

    public String toString(BigDecimal num) {
        num = num.setScale(6, RoundingMode.HALF_UP);
        num = num.stripTrailingZeros();
        String numString = num.toPlainString();
        int pointIndex =  numString.indexOf(".")-3;
        int startIndex = pointIndex== -4 ? numString.length()-3 : pointIndex;
        StringBuilder sb = new StringBuilder(numString);
        while (startIndex > 0) {
                if (sb.charAt(startIndex-1)!='-') sb.insert(startIndex, " ");
                startIndex-=3;
        }
        return sb.toString();
    }

    public void checkSpacedInput(String input) {
        input = input.trim();

        String regex = "^-?(\\d{1,3})( \\d{3})*([.,])?(\\d+)?$";
        if(!input.matches(regex))
            throw new IllegalArgumentException("Invalid input in format 1 000.000000");

    }
}
