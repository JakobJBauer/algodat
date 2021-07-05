package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

import java.util.Arrays;

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
        int k_lower = 0, k_upper = g.numberOfVertices(), k;
        String solution = "";

        while (k_lower != k_upper) {
            k = (k_upper - k_lower) / 2;
            String dimacs = dimacsFromGraph(g, k);
            if ((solution = solver.solve(dimacs)).equals("")) k_upper = k;
            else k_lower = k;
        }

        choseVertices(solution, chosenVertices);
        return numberOfChosenVertices(chosenVertices);
    }

    private String dimacsFromGraph(Graph g, int k) {
        int n_var = 0, n_claus = 0;
        StringBuilder dimacs = new StringBuilder();

        // https://cs.stackexchange.com/questions/70531/reduction-3sat-and-clique
        // Some node is the rth node of the clique
        for (int r = 1; r <= k; r++) {
            for (int i = 1; i <= g.numberOfVertices(); i++) {
                int index = g.numberOfVertices() * (r - 1) + i;
                dimacs.append(index).append(" ");
            }
            dimacs.append(0).append("\n");
        }

        // No node is both, the rth and the sth node of the clique
        for (int s = 1; s <= k; s++) {
            for (int r = 1; r < s; r++) {
                for (int i = 1; i <= g.numberOfVertices(); i++) {
                    int indexR = g.numberOfVertices() * (r - 1) + i;
                    int indexS = g.numberOfVertices() * (s - 1) + i;
                    dimacs.append(-indexR).append(" ").append(-indexS).append(" ");
                }
                dimacs.append(0).append("\n");
            }
        }

        // If there's no edge from i to j then nodes i and j cannot both be in the clique
        for (int s = 1; s <= k; s++) {
            for (int r = 1; r <= k && r != s; r++) {
                for (int j = 1; j <= g.numberOfVertices() ; j++) {
                    for (int i = 1; i < j; i++) {
                        int indexIR = g.numberOfVertices() * (r - 1) + i;
                        int indexJS = g.numberOfVertices() * (s - 1) + j;
                        if (!g.containsEdge(i, j)) {
                            dimacs.append(-indexIR).append(" ").append(-indexJS).append(" ");
                        }
                    }
                }
                dimacs.append(0).append("\n");
            }
        }

        return String.format("p cnf %d %d\n", n_var, n_claus) + dimacs;
    }

    private void choseVertices(String DIMACSSolution, boolean[] chosenVertices) {
        for (String dimac : DIMACSSolution.split(" ")) {
            int index = Integer.parseInt(dimac);
            if (index == 0) return;
            chosenVertices[Math.abs(index)-1] = index > 0;
        }
    }

    private int numberOfChosenVertices(boolean[] chosenVertices) {
        int i = 0;
        for (boolean chosen : chosenVertices) i += chosen ? 1 : 0;
        return i;
    }
}
