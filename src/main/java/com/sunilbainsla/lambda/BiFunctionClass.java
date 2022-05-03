package com.sunilbainsla.lambda;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiFunctionClass {

    public static void main(String[] args) {

       // BiFunction contain return type in the argument list
        BiFunction<Integer,Integer,String> biFunction=(a,b) ->a+b.toString();
        System.out.println(biFunction.apply(2,3));
    }
}
