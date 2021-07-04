package main.java.exercise;

import java.util.HashSet;

public class CLCSInstance {

    private int n;
    private char[][] strings;
    private char[] pattern;

    HashSet<Character> alphabet;

    private StudentSolutionImplementation studentSolutionImplementation;
    private DynamicProgrammingTable tabelle;

    public CLCSInstance(char[] string1, char[] string2, char[] pattern, HashSet<Character> alphabet, StudentSolutionImplementation studentSolutionImplementation) {
        this.strings = new char[2][];
        strings[0] = string1;
        strings[1] = string2;
        this.pattern = pattern;
        this.alphabet = alphabet;

        this.n = Math.max(getN1(), getN2());
        this.studentSolutionImplementation = studentSolutionImplementation;
    }

    public boolean isFeasible(char[] solution) {
        return this.studentSolutionImplementation.isFeasible(this, solution);
    }


    public boolean isFeasibleLCS(char[] solution) {

        int i = -1;
        int j = -1;

        for (int l = 0; l < solution.length; l++) {

            i = CLCSInstance.getNextOccurence(strings[0], solution[l], i + 1);
            j = CLCSInstance.getNextOccurence(strings[1], solution[l], j + 1);

            if (i >= strings[0].length || j >= strings[1].length) {
                return false;
            }
        }

        return true;
    }

    public char[] backtrackingCLCS() throws EmptyCharException {
        if (tabelle == null) {
            this.computeDynamicProgrammingTable();
        }

        char[] res = studentSolutionImplementation.backtrackingCLCS(this, this.getTabelle());

        String temp = "";
        for (char c : res) {
            if (this.alphabet.contains(c)) {
                temp += c;
            }
        }

        if (temp.length() != res.length) {
            throw new EmptyCharException("Some letters in the solution of the backtracking are not in the alphabet,\nprobably empty characters. Please check, if you selected the right k during backtracking.");
        }

        return res;
    }

    public char[] backtrackingLCS() throws EmptyCharException {
        if (tabelle == null) {
            this.computeDynamicProgrammingTable();
        }

        char[] res = studentSolutionImplementation.backtrackingLCS(this, this.getTabelle());

        String temp = "";
        for (char c : res) {
            if (this.alphabet.contains(c)) {
                temp += c;
            }
        }

        if (temp.length() != res.length) {
            throw new EmptyCharException("Some letters in the solution of the backtracking are not in the alphabet,\nprobably empty characters. Please check, if you selected the right k during backtracking.");
        }

        return res;
    }

    public void computeDynamicProgrammingTable() {
        this.tabelle = new DynamicProgrammingTable(this, studentSolutionImplementation);
        studentSolutionImplementation.computeDynamicProgrammingTable(this, this.tabelle);
    }

    public static int getNextOccurence(char[] sequence, char c, int currentPosition) {
        for (int l = currentPosition; l < sequence.length; l++) {
            if (c == sequence[l]) {
                return l;
            }
        }
        return sequence.length;
    }

    public char[] getS1() {
        return strings[0];
    }

    public char[] getS2() {
        return strings[1];
    }

    public char[] getSp() {
        return pattern;
    }

    public int getN1() {
        return strings[0].length;
    }

    public int getN2() {
        return strings[1].length;
    }

    public int getNp() {
        return pattern.length;
    }

    public int getN() { return Math.max(getN1(), getN2()); }

    public DynamicProgrammingTable getTabelle() {
        return this.tabelle;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("string1: ");
        for (int i = 0; i < strings[0].length; i++) {
            sb.append(strings[0][i]);
        }
        sb.append("\nstring2: ");
        for (int i = 0; i < strings[1].length; i++) {
            sb.append(strings[1][i]);
        }
        sb.append("\npattern: ");
        for (int i = 0; i < pattern.length; i++) {
            sb.append(pattern[i]);
        }
        return sb.toString();
    }


    public String toStringOneLine() {
        String res = "";

        // Instanzdaten mit _ ranhängen
        char[] s1 = this.getS1();
        char[] s2 = this.getS2();
        char[] sp = this.getSp();

        res += "s1:";
        for (int i = 0; i < s1.length; i++) {
            res += "&" + s1[i];
        }
        res += "|";

        res += "s2:";
        for (int i = 0; i < s2.length; i++) {
            res += "&" + s2[i];
        }
        res += "|";

        res += "sp:";
        for (int i = 0; i < sp.length; i++) {
            res += "&" + sp[i];
        }

        return res;
    }

    public void print() {
        System.out.println(this.toString());
    }

    public static void printCharArray(char[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]);
        }
        System.out.println();
    }


    public class EmptyCharException extends Exception {
        public EmptyCharException(String message) {
            super(message);
        }
    }
}
