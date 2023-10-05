package com.cs.javademo.java.lamda;

import java.util.function.Function;

public class UseFoo {
    public String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }
}


