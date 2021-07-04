package main.java.exercise.result_implementation;

import main.java.exercise.helper.InstanceType;
import main.java.exercise.helper.ProbeType;

public class InsertResultImplementation extends CommonResultImplementation {

    private int[] result;

    public InsertResultImplementation(InstanceType instanceType, ProbeType probeType, int[] result, double stepsAvg, int m, int numberOfKeys, double stepsNegativeAvg) {
        super(instanceType, probeType, stepsAvg, m, numberOfKeys, stepsNegativeAvg);
        this.result = result;
    }

    public int[] getResult() {
        return result;
    }

}
