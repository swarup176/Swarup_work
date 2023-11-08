package com.cs.javademo.java.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

interface Calculator {

         void sum(int num1 , int num2);

}
public class lamdaTest {

    record Shape (String name) {};  // record class is final

    public static void main(String[] args) {

        Calculator calculator = (num1, num2) -> {
            System.out.println("Sum : " + (num1 + num2));
        };
         calculator.sum(100, 50);



        UseFoo useFoo =new UseFoo();
        Function<String, String> fn =
                parameter -> parameter + " from lambda";
        System.out.println (useFoo.add("Message ", fn));


        System.out.println (Foo.producer());
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Shape("Circle"));
        shapeList.add(new Shape("Triangle"));
        shapeList.add(new Shape("Rectangle"));
        shapeList.forEach(System.out::println);



    }
}
