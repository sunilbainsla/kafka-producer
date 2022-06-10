package com.sunilbainsla.lambda;

import java.util.function.IntBinaryOperator;

public class IntBinaryOperator2 {
    public static void main(String[] args) {
        IntBinaryOperator intBinaryOperator = (int a, int b) ->a * b;

        System.out.println(intBinaryOperator.applyAsInt(12,13));

    }
}
