package com.cs.javademo.java.optionalTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {



    public static Customer getCustomerByEmailId(String email) throws Exception {
        List<Customer> customers = EkartDataBase.getAll();
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(Optional.of(email)))
                .findAny().orElseThrow(()->new Exception("no customer present with this email id"));

    }

    public static void main(String[] args) throws Exception {

        Customer customer=new Customer(101, "john", "test@gmail.com", Arrays.asList("397937955", "21654725"));
        //empty        //of        //ofNullable

        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.isPresent()? "Yes" : "No");
       Optional<String> emailOptional = Optional.of(String.valueOf(customer.getEmail()));
        System.out.println(emailOptional);
        Optional<String> emailOptional2 = Optional.ofNullable(String.valueOf(customer.getEmail()));
        emailOptional2.ifPresent(System.out::println);
         System.out.println(emailOptional2.orElse("default@email.com")  );
         System.out.println(emailOptional2.orElseThrow(()->new IllegalArgumentException("email not present")));
        System.out.println(emailOptional2.map(String::toUpperCase).orElseGet(()->"default email..."));

       getCustomerByEmailId("john@gmail.com");
    }
}