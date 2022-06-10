package com.sunilbainsla.lambda;

public interface MyInterface {
    public void testImpl();
    default public void  testImpl2()
    {
    System.out.println("Sunil Kumar");
    }
    default String  testImpl3()
    {
    return "Sunil test Impl3";
    }

    static String testImpl4() {
        return "Static testImpl4 impl";
    }
}
