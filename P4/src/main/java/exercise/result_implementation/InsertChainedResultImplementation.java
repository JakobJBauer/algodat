package main.java.exercise.result_implementation;

import main.java.exercise.helper.ChainElement;
import main.java.exercise.helper.InstanceType;
import main.java.exercise.helper.ProbeType;

public class InsertChainedResultImplementation extends CommonResultImplementation {

    private ChainElement[] result;

    public InsertChainedResultImplementation(InstanceType instanceType, ProbeType probeType, ChainElement[] result, double stepsAvg, int m, int numberOfKeys, double stepsNegativeAvg) {
        super(instanceType, probeType, stepsAvg, m, numberOfKeys, stepsNegativeAvg);
        this.result = result;
    }

    public ChainElement[] getResult() {
        return result;
    }
}
