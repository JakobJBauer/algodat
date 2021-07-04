package main.java.exercise.instace_implementation;

import main.java.exercise.helper.InstanceType;
import main.java.exercise.helper.ProbeType;

public class InsertInstanceImplementation extends CommonInstanceImplementation {

    private int[] keys;
    private int numberOfKeys;
    private int m;
    private ProbeType probeType;
    private String[] expectedResult;
    private int[] unusedKeys;

    public InsertInstanceImplementation(String groupName, int number, InstanceType instanceType, int[] keys, int numberOfKeys, int m, ProbeType probeType, String[] expectedResult, int[] unusedKeys) {
        super(groupName, number, instanceType);
        this.keys = keys;
        this.numberOfKeys = numberOfKeys;
        this.m = m;
        this.probeType = probeType;
        this.expectedResult = expectedResult;
        this.unusedKeys = unusedKeys;
    }

    public int[] getKeys() {
        return keys;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public int getM() {
        return m;
    }

    public ProbeType getProbeType() {
        return probeType;
    }

    public String[] getExpectedResult() {
        return expectedResult;
    }

    public int[] getUnusedKeys() {
        return unusedKeys;
    }
}
