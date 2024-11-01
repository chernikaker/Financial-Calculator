package com.chernikaker.logic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Calculator {

    private static final Map<String, Integer> priority = Map.of(
            "+",2,
            "-",2,
            "/",1,
            "*",1);

    public static BigDecimal calculateExpression(List<BigDecimal> nums, List<String> operations) {
        if(nums.size()!=4||operations.size()!=3){
            throw new IllegalArgumentException("Invalid nums or operations amount");
        }
        BigDecimal middleResult = performCalculation(nums.get(1), operations.get(1), nums.get(2));
        if(priority.get(operations.get(0)) == priority.get(operations.get(2))||
        priority.get(operations.get(0)) < priority.get(operations.get(2))) {
            BigDecimal firstResult = performCalculation(nums.get(0), operations.get(0), middleResult);
            return performCalculation(firstResult, operations.get(2), nums.get(3));
        }
        else {
            BigDecimal secondResult = performCalculation(middleResult, operations.get(2), nums.get(3));
            return performCalculation(nums.get(0), operations.get(0), secondResult);
        }
    }

    private static BigDecimal performCalculation(BigDecimal a, String operation, BigDecimal b) {
        Counter c = new Counter();
        return switch (operation) {
            case "+" -> c.add(a, b);
            case "-" -> c.subtract(a, b);
            case "*" -> c.multiply(a, b);
            case "/" -> c.divide(a, b);
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }
}
