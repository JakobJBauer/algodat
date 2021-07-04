package main.java.exercise;

import main.java.framework.PersistAs;
import main.java.framework.Result;

public class ResultImplementation implements Result {

    private String problemType;

    @PersistAs("duration")
    private long duration;

    @PersistAs("n")
    private int n;

    @PersistAs("alphabetsize")
    private int alphabetsize;

    @PersistAs("npattern")
    private int npattern;

    @PersistAs("lengthSolCLCS")
    private int lengthSolCLCS;

    @PersistAs("lengthSolLCS")
    private int lengthSolLCS;

    @PersistAs("solCLCS")
    private String solCLCS;

    @PersistAs("solLCS")
    private String solLCS;

        @PersistAs("instance")
    private String instancestring;

    @PersistAs("dynamicProgrammingTable")
    private String dynamicProgrammingTable;

    private boolean solutionIsFeasible;                  // Kandidatenlösung für isFeasible
    private char[] solutionCLCS;                        // Kandidatenlösung für CLCS
    private char[] solutionLCS;                         // Kandidatenlösung für LCS

    public ResultImplementation(String problemType, long duration, int n, int npattern, int alphabetsize,
                                String dynamicProgrammingTable, boolean solutionIsFeasible,
                                char[] solutionCLCS, char[] solutionLCS, String instanzstring) {
        this.problemType = problemType;
        this.n = n;
        this.npattern = npattern;
        this.alphabetsize = alphabetsize;
        this.duration = duration;
        this.instancestring = instanzstring;
        this.solutionIsFeasible = solutionIsFeasible;
        this.solutionCLCS = solutionCLCS;
        this.solutionLCS = solutionLCS;

        lengthSolCLCS = solutionCLCS != null ? solutionCLCS.length : -1;
        lengthSolLCS = solutionLCS != null ? solutionLCS.length : -1;

        solLCS = "";
        if (solutionLCS == null) {
            solLCS = "---";
        }
        else {
            solLCS = "lcs";
            for (int i = 0; i < solutionLCS.length; i++) {
                solLCS += "&" + solutionLCS[i];
            }
        }

        solCLCS = "";
        if (solutionCLCS == null) {
            solCLCS = "---";
        }
        else {
            solCLCS = "clcs";
            for (int i = 0; i < solutionCLCS.length; i++) {
                solCLCS += "&" + solutionCLCS[i];
            }
        }

        this.dynamicProgrammingTable = dynamicProgrammingTable;
    }

    public String getProblemType() {
        return problemType;
    }

    public boolean getSolutionIsFeasible() {
        return solutionIsFeasible;
    }

    public char[] getSolutionCLCS() { return solutionCLCS; }

    public char[] getSolutionLCS() { return solutionLCS; }
}
