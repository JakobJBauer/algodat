package main.java.exercise.helper;

import main.java.exercise.StudentSolutionImplementation;

public class Probe {

    private StudentSolutionImplementation studentSolutionImplementation;

    private ProbeType type;

    private int m;

    public Probe(StudentSolutionImplementation studentSolutionImplementation,
                 ProbeType type,
                 int m) {
        this.studentSolutionImplementation = studentSolutionImplementation;
        this.type = type;
        this.m = m;
    }

    public int evaluate(int key, int i) {
        switch(this.type) {
            case LINEARES_SONDIEREN:
                return this.studentSolutionImplementation.linearesSondieren(key, i, m);
            case QUADRATISCHES_SONDIEREN:
                return this.studentSolutionImplementation.quadratischesSondieren(key, i, m);
            case DOUBLE_HASHING:
                return this.studentSolutionImplementation.doubleHashing(key, i, m);
            default:
                return -1;
        }
    }
}
