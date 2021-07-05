package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Jakob Johannes", // Vorname
                "Bauer", // Nachname
                "12002215" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung mit Polynomialzeitreduktion
    public int findMaxClique(Graph g, TimedSolver solver, boolean[] chosenVertices) {
        int k_lower = 0, k_upper = g.numberOfVertices() - 1, k;
        String solution = "";

        while (k_lower <= k_upper) {
            k = (k_upper + k_lower) / 2;
            String dimacs = dimacsFromGraph(g, k);
            if (solver.solve(dimacs).equals("")) k_upper = k-1;
            else {
                    k_lower = k+1;
                    solution = solver.solve(dimacs);
            }
        }

        choseVertices(solution, chosenVertices, g.numberOfVertices());
        return numberOfChosenVertices(chosenVertices);
    }

    private String dimacsFromGraph(Graph g, int k) {
        int n_claus = 0;
        StringBuilder dimacs = new StringBuilder();

        // Some node is the rth node of the clique
        for (int r = 1; r <= k; r++) {
            for (int i = 1; i <= g.numberOfVertices(); i++) {
                int index = encode(g.numberOfVertices(), r, i);
                dimacs.append(index).append(" ");
            }
            dimacs.append(0).append("\n");
            n_claus++;
        }

        // No node is both, the rth and the sth node of the clique
        for (int i = 1; i <= g.numberOfVertices(); i++) {
            for (int r = 1; r <= k; r++) {
                for (int s = r + 1; s <= k; s++) {
                    int indexR = encode(g.numberOfVertices(), r, i);
                    int indexS = encode(g.numberOfVertices(), s, i);
                    dimacs.append(-indexR).append(" ").append(-indexS).append(" ").append(0).append("\n");
                    n_claus++;
                }
            }
        }

        // If there's no edge from i to j then nodes i and j cannot both be in the clique
        for (int r = 1; r <= k; r++) {
            for (int s = 1; s <= k; s++) {
                if (r == s) continue;
                for (int i = 1; i <= g.numberOfVertices(); i++) {
                    for (int j = i + 1; j <= g.numberOfVertices(); j++) {
                        if (!g.containsEdge(i, j)) {
                            int indexIR = encode(g.numberOfVertices(), r, i);
                            int indexJS = encode(g.numberOfVertices(), s, j);
                            dimacs.append(-indexIR).append(" ").append(-indexJS).append(" ").append(0).append("\n");
                            n_claus++;
                        }
                    }
                }
            }
        }

        return String.format("p cnf %d %d\n", g.numberOfVertices() * k, n_claus) + dimacs;
    }

    private void choseVertices(String DIMACSSolution, boolean[] chosenVertices, int n) {
        for (String dimac : DIMACSSolution.split(" ")) {
            if (dimac.equals("0")) return;
            if (!dimac.startsWith("-")) {
                int index = decode(Integer.parseInt(dimac), n);
                chosenVertices[index-1] = true;
            }
        }
    }

    private int numberOfChosenVertices(boolean[] chosenVertices) {
        int i = 0;
        for (boolean chosen : chosenVertices) i += chosen ? 1 : 0;
        return i;
    }

    private int decode(int encoded, int n) {
        return 1 + ((encoded - 1) % n);
    }

    private int encode(int n, int i, int v) {
        return n * (i - 1) + v;
    }
}