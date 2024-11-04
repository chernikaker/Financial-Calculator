package com.chernikaker.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Calculator {

    private static final Map<String, Integer> priority = Map.of(
            "+",2,
            "-",2,
            "/",1,
            "*",1);

    public static final BigDecimal MAX_VALUE = new BigDecimal("1000000000000.000000");
    public static final BigDecimal MIN_VALUE = new BigDecimal("-1000000000000.000000");


    public static BigDecimal calculateExpression(List<BigDecimal> nums, List<String> operations) {
        if(nums.size()!=4||operations.size()!=3){
            throw new IllegalArgumentException("Invalid nums or operations amount");
        }

        for(BigDecimal num : nums){
            if(num.compareTo(MAX_VALUE)>0 || num.compareTo(MIN_VALUE)<0) {
                throw new IllegalArgumentException("Input number "+num+" is out of range");
            }
        }

        BigDecimal middleResult = performCalculation(nums.get(1), operations.get(1), nums.get(2),2);
        if(priority.get(operations.get(0)) == priority.get(operations.get(2))||
        priority.get(operations.get(0)) < priority.get(operations.get(2))) {
            BigDecimal firstResult = performCalculation(nums.get(0), operations.get(0), middleResult,1);
            BigDecimal result =  performCalculation(firstResult, operations.get(2), nums.get(3),3);

            return result;
        }
        else {
            BigDecimal secondResult = performCalculation(middleResult, operations.get(2), nums.get(3),3);
            BigDecimal result =  performCalculation(nums.get(0), operations.get(0), secondResult,2);

            return result;
        }
    }

    private static BigDecimal performCalculation(BigDecimal a, String operation, BigDecimal b, int op) {
        Counter c = new Counter();
        return switch (operation) {
            case "+" -> c.add(a, b,op);
            case "-" -> c.subtract(a, b,op);
            case "*" -> c.multiply(a, b, op);
            case "/" -> c.divide(a, b, op);
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }

    public static BigDecimal round(String method, BigDecimal num){
        return switch (method) {
            case "Math" -> num.setScale(0, RoundingMode.HALF_UP);
            case "Truncation" -> num.setScale(0, RoundingMode.DOWN);
            case "Bank" -> {
                if (num.subtract(num.setScale(0, RoundingMode.FLOOR))
                        .compareTo(new BigDecimal("0.5")) == 0) {
                    yield num.setScale(0, RoundingMode.HALF_EVEN);
                }
                yield num.setScale(0, RoundingMode.HALF_UP);
            }
            default -> throw new IllegalArgumentException("Invalid method: " + method);
        };
    }
}
