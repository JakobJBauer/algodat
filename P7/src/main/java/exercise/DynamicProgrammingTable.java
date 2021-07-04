package main.java.exercise;


public class DynamicProgrammingTable {

    private CLCSInstance instanz;
    private StudentSolutionImplementation studentSolutionImplementation;
    private int[][][] tabelle;

    public DynamicProgrammingTable(CLCSInstance instanz, StudentSolutionImplementation studentSolutionImplementation) {
        this.instanz = instanz;
        this.studentSolutionImplementation = studentSolutionImplementation;
        this.tabelle = new int[instanz.getNp() + 1][instanz.getN1() + 1][instanz.getN2() + 1];
    }

    public void computeDynamicProgrammingTable(CLCSInstance instanz) {
        studentSolutionImplementation.computeDynamicProgrammingTable(instanz, this);
    }

    public int get(int k, int i1, int i2) {
        return tabelle[k][i1][i2];
    }

    public void set(int k, int i1, int i2, int value) {
        tabelle[k][i1][i2] = value;
    }


    private String formatRight(String s, int digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < digits; i++) {
            sb.append(" ");
        }
        sb.append(s);
        return sb.toString();
    }


    private String matrixToString(int k, int digits) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatRight("#", 2 * digits + 1));

        for (int j = 0; j < instanz.getN2(); j++) {
            sb.append(" " + formatRight(Character.toString(instanz.getS2()[j]), digits));
        }

        for (int i = 0; i <= instanz.getN1(); i++) {
            sb.append("\n");
            if (i == 0) {
                sb.append(formatRight("#", digits));
            } else {
                sb.append(formatRight(Character.toString(instanz.getS1()[i-1]), digits));
            }
            for (int j = 0; j < tabelle[k][i].length; j++) {
                sb.append(" " + formatRight(Integer.toString(tabelle[k][i][j]), digits));
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {

        int digits = 0;
        for (int k = 0; k < tabelle.length; k++) {
            for (int i = 0; i < tabelle[k].length; i++) {
                for (int j = 0; j < tabelle[k][i].length; j++) {
                    digits = Math.max(digits, Integer.toString(tabelle[k][i][j]).length());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < tabelle.length; k++) {
            // Drucke Tabelleneintrag k
            if (k > 0) {
                sb.append("\n");
            }
            sb.append("Tabelle[" + k + "][][]:\n");
            sb.append(matrixToString(k, digits));
        }

        return sb.toString();
    }

    public String toStringOneLine() {
        String dynamicProgrammingTable = "";

        char[] s1 = this.instanz.getS1();
        char[] s2 = this.instanz.getS2();

        for (int k = 0; k < tabelle.length; k++) {
            if (k != 0) {
                dynamicProgrammingTable += "~";
            }
            // String 2 einfügen
            dynamicProgrammingTable += "&#";
            for (int j = 0; j < s2.length; j++) {
                dynamicProgrammingTable += "&" + s2[j];
            }
            dynamicProgrammingTable += "|";

            for (int i = 0; i < tabelle[k].length; i++) {
                if (i != 0) {
                    dynamicProgrammingTable += "|";
                }
                if (i == 0) {
                    dynamicProgrammingTable += "#&";
                }
                else {
                    dynamicProgrammingTable += s1[i-1] + "&";
                }

                for (int j = 0; j < tabelle[k][i].length; j++) {
                    if (j != 0) {
                        dynamicProgrammingTable += "&";
                    }
                    String zahl = tabelle[k][i][j] < -99? "-Inf" : Integer.toString(tabelle[k][i][j]);
                    dynamicProgrammingTable += zahl;
                }
            }
        }
        return dynamicProgrammingTable;
    }

    public void print() {
        System.out.println(this.toString());
    }
}
