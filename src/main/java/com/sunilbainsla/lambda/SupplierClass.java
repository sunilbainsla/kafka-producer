package com.sunilbainsla.lambda;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class SupplierClass {

    public static void main(String[] args) {
        // does not accept any input but return out put
        Supplier<LocalDateTime> s = () -> LocalDateTime.now();
        LocalDateTime time = s.get();
        System.out.println(time);

    }
}
