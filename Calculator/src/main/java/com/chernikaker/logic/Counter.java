package com.chernikaker.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Counter {

    private static final BigDecimal MAX_VALUE = new BigDecimal("1000000000000.000000");
    private static final BigDecimal MIN_VALUE = new BigDecimal("-1000000000000.000000");


    public BigDecimal add(BigDecimal a, BigDecimal b) {
        if(a.compareTo(MAX_VALUE)>0 || a.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Number 1 is out of range");
        }

        if(b.compareTo(MAX_VALUE)>0 || b.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Number 2 is out of range");
        }
        BigDecimal result = a.add(b);
        if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Result is out of range");
        }
        return result;
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        if(a.compareTo(MAX_VALUE)>0 || a.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Number 1 is out of range");
        }

        if(b.compareTo(MAX_VALUE)>0 || b.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Number 2 is out of range");
        }
        BigDecimal result = a.subtract(b);
        if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Result is out of range");
        }
        return result;
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if(a.compareTo(MAX_VALUE)>0 || a.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Number 1 is out of range");
        }

        if(b.compareTo(MAX_VALUE)>0 || b.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Number 2 is out of range");
        }
        BigDecimal result = a.multiply(b);
        if(result.compareTo(MAX_VALUE)>0 || result.compareTo(MIN_VALUE)<0) {
            throw new IllegalArgumentException("Result is out of range");
        }
        return result;
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        try {
            if (a.compareTo(MAX_VALUE) > 0 || a.compareTo(MIN_VALUE) < 0) {
                throw new IllegalArgumentException("Number 1 is out of range");
            }

            if (b.compareTo(MAX_VALUE) > 0 || b.compareTo(MIN_VALUE) < 0) {
                throw new IllegalArgumentException("Number 2 is out of range");
            }
            BigDecimal result = a.divide(b, 6, RoundingMode.HALF_UP);
            if (result.compareTo(MAX_VALUE) > 0 || result.compareTo(MIN_VALUE) < 0) {
                throw new IllegalArgumentException("Result is out of range");
            }
            return result;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Error: division by zero");
        }
    }

}
