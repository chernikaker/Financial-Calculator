package com.chernikaker.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.chernikaker.logic.Calculator.MAX_VALUE;
import static com.chernikaker.logic.Calculator.MIN_VALUE;

public class Counter {


    public BigDecimal add(BigDecimal a, BigDecimal b, int op) {
        BigDecimal result = a.add(b);
        result = result.setScale(10, RoundingMode.HALF_UP);
        result = result.stripTrailingZeros();
        if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Result of operation #"+op+" ="+result.toPlainString()+" (add) is out of range");
        }
        return result;
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b, int  op) {

        BigDecimal result = a.subtract(b);
        result = result.setScale(10, RoundingMode.HALF_UP);
        result = result.stripTrailingZeros();
        if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Result of operation #"+op+" ="+result.toPlainString()+" (sub) is out of range");
        }
        return result;
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b, int op) {
        BigDecimal result = a.multiply(b);
        result = result.setScale(10, RoundingMode.HALF_UP);
        result = result.stripTrailingZeros();
        if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Result of operation #"+op+" ="+result.toPlainString()+" (mult) is out of range");
        }
        return result;
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b, int op) {
        try {
            BigDecimal result = a.divide(b,10, RoundingMode.HALF_UP);
            result = result.stripTrailingZeros();
            if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
                throw new IllegalArgumentException("Result of operation #"+op+" ="+result.toPlainString()+" (div) is out of range");
            }
            return result;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Error: division by zero");
        }
    }

}
