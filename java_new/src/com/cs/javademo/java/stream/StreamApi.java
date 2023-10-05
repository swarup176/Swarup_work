package com.cs.javademo.java.stream;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://stackify.com/streams-guide-java-8/
public class StreamApi {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 1, 1);
        System.out.println(integers.stream().reduce(23, (a, b) -> a + b));
        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect); //[A, B, C, D]
        // Extra, streams apply to any data type.
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
        num.stream().map(n -> n * 2).forEach( t -> System.out.print(t));
         //[2, 4, 6, 8, 10]
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));              // true
        System.out.println(predicate.negate().test("foo"));     // false
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
                .filter((s) -> s.startsWith("A") || s.startsWith("B")).forEach(System.out::println);
        List<Person> persons =Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        Map<Integer, List<Person>> personsByAge = persons
                .stream().collect(Collectors.groupingBy(p -> p.age));
        personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
        Map<Integer, String> map = persons.stream().collect(Collectors.toMap( p -> p.age,p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));
        System.out.println(map);
        System.out.println(persons.stream().filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age.")));
        persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);
        persons.stream().peek(p -> p.age= p.age*2).findFirst().ifPresent(System.out::println);
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
        System.out.println(intList.stream().mapToInt(Integer::intValue).sum());

        Stream.iterate(1, i -> i + 1).takeWhile(n -> n <= 10).map(x -> x * x).forEach(System.out::println);
      //  Stream.iterate(1, i -> i + 1).dropWhile(n -> n <= 10).map(x -> x * x).forEach(System.out::println);
    }
}


class Person {
    String name;

    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override

    public String toString() {
        return name + ":"+ age;

    }
}