package com.springboot.restfull_web_services.versioning;

public class Person {
    private Name name;
    public Person(String firstName, String lastName) {
        this.name =  new Name(firstName, lastName);
    }

    public Name getName() {
        return name;
    }
}
