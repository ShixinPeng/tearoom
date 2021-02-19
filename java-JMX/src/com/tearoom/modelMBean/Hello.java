package com.tearoom.modelMBean;

public class Hello {
    private String name;


    public void sayHi(){
        System.out.println( "hello world  " + name);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
