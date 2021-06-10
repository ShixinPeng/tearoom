package com.chapter.io;

import java.io.Serializable;

public class Gorilla implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private boolean friendly;
    private transient String favoriteFood;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Gorilla(String name, int age, boolean friendly) {
        this.name = name;
        this.age = age;
        this.friendly = friendly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toString() {
        return "Gorilla{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendly=" + friendly +
                '}';
    }
}
