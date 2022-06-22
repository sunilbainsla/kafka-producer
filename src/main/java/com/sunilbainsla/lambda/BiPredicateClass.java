package com.sunilbainsla.lambda;

import java.util.function.BiPredicate;

public class BiPredicateClass {
    // predicate will return will booleans
    public static void main(String[] args) {

        //BiPredicate<Integer,Integer> biPredicate=(a,b) -> a+b>10;
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a + b > 10;
        System.out.println(biPredicate.test(4, 5));
        
    }
}
