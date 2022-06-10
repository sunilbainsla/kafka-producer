package com.sunilbainsla.lambda;

public class MyInterfaceImpl implements  MyInterface{
    @Override
    public void testImpl() {
    System.out.println("test Impl Class");
    }
    @Override
    public void testImpl2() {
        System.out.println("testImpl2");

    }


    public static void main(String[] args) {
        MyInterfaceImpl impl=new MyInterfaceImpl();
        System.out.println(impl.testImpl3());
        System.out.println(MyInterface.testImpl4());

    }
}
