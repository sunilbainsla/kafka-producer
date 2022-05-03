package com.sunilbainsla.lambda;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiConsumerClass {

    public static void main(String[] args) {


        BiConsumer<Integer,Integer> biConsumer
                =(a,b) ->System.out.println(a+b.toString());
        biConsumer.accept(2,3);
    }
}
