package com.cs.javademo.java.stream;

import java.util.List;

public class Employee {
    String firstName;
    String LastName;

    Double Salary;
    List<String> projects;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Double getSalary() {
        return Salary;
    }

    public List<String> getProjects() {
        return projects;
    }

    public Employee(String firstName, String lastName, Double salary, List<String> projects) {
        this.firstName = firstName;
        LastName = lastName;
        Salary = salary;
        this.projects = projects;
    }
}
