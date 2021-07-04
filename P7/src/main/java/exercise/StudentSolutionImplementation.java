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

    // Implementieren Sie hier Ihre Lösung für isFeasible()
    public boolean isFeasible(CLCSInstance instance, char[] solution) {

        return false;
    }


    // Implementieren Sie hier Ihre Lösung für die Erstellung der Tabelle für die Dynamische Programmierung
    public void computeDynamicProgrammingTable(CLCSInstance instance, DynamicProgrammingTable table) {
        
    }


    // Implementieren Sie hier Ihre Lösung für das Backtracking im CLCS
    public char[] backtrackingCLCS(CLCSInstance instance, DynamicProgrammingTable table) {

        return null;
    }


    // Implementieren Sie hier Ihre Lösung für das Backtracking im LCS
    public char[] backtrackingLCS(CLCSInstance instance, DynamicProgrammingTable table) {

        return null;
    }
}
