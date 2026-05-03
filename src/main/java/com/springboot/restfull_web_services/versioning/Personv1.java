package com.springboot.restfull_web_services.versioning;

public class Personv1 {
    private String name;
    public Personv1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Personv1{" +
                "name='" + name + '\'' +
                '}';
    }
}
