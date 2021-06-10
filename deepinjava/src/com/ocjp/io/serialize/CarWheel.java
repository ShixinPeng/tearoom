package com.ocjp.io.serialize;

import java.io.Serializable;

public class CarWheel extends Car implements Serializable {

    private int size;


    public CarWheel(){
        super("Unknown");
        this.size = 0;
    }
    public CarWheel(int size) {
        super("UnKnown");
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CarWheel{" +
                super.toString() +
                "size=" + size +
                '}';
    }
}
