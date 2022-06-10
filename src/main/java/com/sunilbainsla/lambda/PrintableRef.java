package com.sunilbainsla.lambda;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Arrays;
import java.util.Comparator;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
interface RefInterface {
     String print();
}

public class PrintableRef {
    public static void main(String[] args) {
        Function<Integer, Double> myfun = (input) -> Math.sqrt(input);
        System.out.println(myfun.apply(4));
        Function<Integer, Double> myfun2 = Math::sqrt;
        System.out.println(myfun2.apply(4));
        BiFunction<Integer, Integer, Integer> myfun3 = (a, b) -> addMyNum(a, b);
        System.out.println(myfun3.apply(4, 5));

        BiFunction<Integer, Integer, Integer> myfun4 = PrintableRef::addMyNum;
        System.out.println(myfun4.apply(4, 5));
        PrintableRef pObj= new PrintableRef();
        pObj.display("sddsd");

        RefInterface refInterface=()->pObj.display("kkkkkk");
        System.out.println(refInterface.print());
        String arryList[]={"A","I","O","U","a","i","o","u"};
        Arrays.sort(arryList,(a,b) -> a.compareToIgnoreCase(b));

        Arrays.stream(arryList).forEach(System.out::println);
    }

    public static int addMyNum(Integer one, Integer two) {
        return one + two;
    }

    public String display(String msg) {

        System.out.println(msg.toUpperCase());
        return msg;

    }
    public static <T> void sortmyWay(T[] a, Comparator<? extends T> c) {
        Arrays.stream(a).count();
    }



}
