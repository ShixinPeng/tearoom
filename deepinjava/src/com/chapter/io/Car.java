package com.chapter.io;

public class Car {

    private String name;

    public Car(){
        System.out.println("Car()");
        this.name = "default";
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'';
    }
}
