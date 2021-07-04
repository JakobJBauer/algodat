package main.java.exercise.instace_implementation;

import main.java.exercise.helper.InstanceType;

public class HashInstanceImplementation extends CommonInstanceImplementation {

    private int key;

    private int i;

    private int m;

    private int expectedPosition;

    public HashInstanceImplementation(String groupName, int number, InstanceType instanceType, int key, int i, int m, int expectedPosition) {
        super(groupName, number, instanceType);
        this.key = key;
        this.i = i;
        this.m = m;
        this.expectedPosition = expectedPosition;
    }

    public int getKey() {
        return key;
    }

    public int getI() {
        return i;
    }

    public int getM() {
        return m;
    }

    public int getExpectedPosition() {
        return expectedPosition;
    }
}
