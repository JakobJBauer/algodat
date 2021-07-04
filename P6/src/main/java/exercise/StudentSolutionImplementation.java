package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;


public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "", // Vorname
                "", // Nachname
                "" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für triviale obere Schranke
    public int trivialUpperBound(SubGraph g) {
        return 0;
    }

    // Implementieren Sie hier Ihre Lösung für obere Schranke nach Hansen
    public int hansenUpperBound(SubGraph g) {
        return 0;
    }

    // Implementieren Sie hier Ihre Lösung basierend auf der gegebenen greedy Vertex Cover Heuristik
    public int vertexCoverBasedHeuristic(SubGraph g) {
        return 0;
    }

    // Implementieren Sie hier Ihre Lösung des definierten Branch-and-Bound Algorithmus für das MISP basierend auf der SubProblem Datenstruktur
    public int maximumIndependentSetBranchAndBoundSolver(SubProblem rootProblem, SubProblemQueue Q) {
        return 0;
    }

}
