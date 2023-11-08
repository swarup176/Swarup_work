package com.cs.javademo.java.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestExample {

    static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(
                new Employee("Shabbir", "Dawoodi", 5000.0, List.of("Project 1", "Project 2"))
        );

        employees.add(
                new Employee("Nikhil", "Gupta", 6000.0, List.of("Project 1", "Project 3"))
        );

        employees.add(
                new Employee("Shivam", "Kumar", 5500.0, List.of("Project 3", "Project 4"))
        );

    }

    public static void test1(String[] args) {


        //foreach
        employees.stream()
                .forEach(employee -> System.out.println(employee));


        //map
        //collect
        Set<Employee> increasedSal =
                employees.stream()
                        .map(employee -> new Employee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 1.10,
                                employee.getProjects()
                        ))
                        .collect(Collectors.toSet());
        System.out.println(increasedSal);


        //filter
        //findFirst
        List<Employee> filterEmployee =
                employees
                        .stream()
                        .filter(employee -> employee.getSalary() > 5000.0)
                        .map(employee -> new Employee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 1.10,
                                employee.getProjects()
                        ))
                        .collect(Collectors.toList());

        System.out.println(filterEmployee);


        Employee firstEmployee =
                employees
                        .stream()
                        .filter(employee -> employee.getSalary() > 7000.0)
                        .map(employee -> new Employee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 1.10,
                                employee.getProjects()
                        ))
                        .findFirst()
                        .orElse(null);
        System.out.println(firstEmployee);


        //flatMap
        String projects =
                employees
                        .stream()
                        .map(employee -> employee.getProjects())
                        .flatMap(strings -> strings.stream())
                        .collect(Collectors.joining(","));
        System.out.println(projects);

        //short Circuit operations
        List<Employee> shortCircuit =
                employees
                        .stream()
                        .skip(1)
                        .limit(1)
                        .collect(Collectors.toList());
        System.out.println(shortCircuit);


        //Finite Data
        Stream.generate(Math::random)
                .limit(5)
                .forEach(value -> System.out.println(value));

        //sorting
        List<Employee> sortedEmployees =
                employees
                        .stream()
                        .sorted((o1, o2) -> o1.getFirstName()
                                .compareToIgnoreCase(o2.getFirstName()))
                        .collect(Collectors.toList());
        System.out.println(sortedEmployees);


        //min or max
        employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        //reduce
        Double totalSal =
                employees
                        .stream()
                        .map(employee -> employee.getSalary())
                        .reduce(0.0, Double::sum);
        System.out.println(totalSal);


            String input= "mynameisswarup";
            Map<String, Long> map= Arrays.stream(input.split("")).collect(Collectors.groupingBy( Function.identity() , LinkedHashMap::new,Collectors.counting()));
// find each char occourance

            System.out.println(map  );
            //find duplicate element
            System.out.println(map.entrySet().stream().filter(x-> x.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList())  );
            System.out.println(map.entrySet().stream().filter(x-> x.getValue()==1).findFirst().get().getKey()  );

            int array[] ={ 5, 4 ,8 ,41,49 , 9 ,7};
            System.out.println(Arrays.stream(array).distinct().boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().get() );
            String [] charList = { "I","Love" , "Switzerland" , "and","India"};

            System.out.println( Arrays.stream(charList).reduce( (a,b)-> a.length()>b.length()?a:b).get()  );

            System.out.println(Arrays.stream(array).boxed().map( s-> s +"").filter( s -> s.startsWith("4")).collect(Collectors.toList())   );

            System.out.println(String.join("-", Arrays.stream(array).boxed().map( s-> s +"").collect(Collectors.toList()) ));


            Map <String , Integer> map1 = new HashMap<>();
            map1.put( "swarup" , 1600   );
            map1.put( "sourav" , 1400   );
            map1.put( "Dhiman" , 1200   );
            map1.put( "Bagchi" , 1400   );
            map1.put( "Sachim" , 1500   );
            System.out.println( String.valueOf(map1.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList()).get(1)));



            System.out.println(map1.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue,
                    Collectors.mapping(Map.Entry::getKey, Collectors.toList()))) );

    }

}
