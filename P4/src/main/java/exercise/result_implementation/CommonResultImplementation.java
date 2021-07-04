package main.java.exercise.result_implementation;

import main.java.exercise.helper.InstanceType;
import main.java.exercise.helper.ProbeType;
import main.java.framework.PersistAs;
import main.java.framework.Result;

public class CommonResultImplementation implements Result {

    @PersistAs("instanceType")
    private InstanceType instanceType;

    @PersistAs("probeType")
    private ProbeType probeType;

    @PersistAs("stepsAvg")
    private double stepsAvg;

    @PersistAs("m")
    private int m;

    @PersistAs("numberOfKeys")
    private int numberOfKeys;

    @PersistAs("stepsNegativeAvg")
    private double stepsNegativeAvg;

    public CommonResultImplementation(InstanceType instanceType, ProbeType probeType, double stepsAvg, int m, int numberOfKeys, double stepsNegativeAvg) {
        this.instanceType = instanceType;
        this.probeType = probeType;
        this.stepsAvg = stepsAvg;
        this.m = m;
        this.numberOfKeys = numberOfKeys;
        this.stepsNegativeAvg = stepsNegativeAvg;
    }

    public InstanceType getInstanceType() {
        return instanceType;
    }
}
