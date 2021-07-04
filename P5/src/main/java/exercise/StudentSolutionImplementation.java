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

    // Implementieren Sie hier Ihre Lösung mit Polynomialzeitreduktion
    public int findMaxClique(Graph g, TimedSolver solver, boolean[] chosenVertices) {
        return 0;
    }
}
