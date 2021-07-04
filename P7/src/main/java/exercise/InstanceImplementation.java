package main.java.exercise;

import main.java.framework.Instance;

public class InstanceImplementation implements Instance {

    private String groupName;                           // Art der Instanz
    private int number;                                 // Nummer des Runs
    private CLCSInstance instanz;                       // Instanz
    private int size;                                   // Größe der Instanz
    private int alphabetsize;
    private char[] solutionCandit;                      // Kandidatenlösung für isFeasible
    private boolean solutionIsFeasible;                 // Erwarteter Outcome für isFeasible
    private int optimumLCS;                             // Optimaler Wert der Länge für das LCS
    private int optimumCLCS;                            // Optimaler Wert der Länge für das CLCS

    public InstanceImplementation(String groupName, int number, CLCSInstance instanz, int size, int alphabetsize,
                                  char[] solutionCandit, boolean solutionIsFeasible, int optimumLCS, int optimumCLCS) {
        this.groupName = groupName;
        this.number = number;
        this.instanz = instanz;
        this.size = size;
        this.alphabetsize = alphabetsize;
        this.solutionCandit = solutionCandit;
        this.solutionIsFeasible = solutionIsFeasible;
        this.optimumLCS = optimumLCS;
        this.optimumCLCS = optimumCLCS;
    }

    @Override
    public String getGroupName() {
        return this.groupName;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    public CLCSInstance getCLCSInstance() { return this.instanz; }

    public int getSize() { return this.size; }

    public int getAlphabetSize() { return this.alphabetsize; }

    public char[] getSolutionCandit() { return this.solutionCandit; }

    public boolean getSolutionIsFeasible() { return this.solutionIsFeasible; }

    public int getOptimumLCS() { return this.optimumLCS; }

    public int getOptimumCLCS() { return this.optimumCLCS; }
}
