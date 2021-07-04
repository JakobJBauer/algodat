package main.java.exercise.result_implementation;

import main.java.exercise.helper.InstanceType;
import main.java.exercise.helper.ProbeType;

public class HashResultImplementation extends CommonResultImplementation {

    private int position;

    public HashResultImplementation(InstanceType instanceType, int position) {
        super(instanceType, ProbeType.NONE,0, 0 , 0, 0);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
