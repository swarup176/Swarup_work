package com.cs.javademo.java.lamda;

@FunctionalInterface
public interface Foo {
    String method(String string);

   // String method1(String string); not possible as it functional interface


    static String producer() {  //default  for default method instead of static method whihc is class level
        return "Swarup Das";
    }


}
