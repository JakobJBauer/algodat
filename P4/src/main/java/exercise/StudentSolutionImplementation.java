package main.java.exercise;

import main.java.exercise.helper.ChainElement;
import main.java.exercise.helper.HashTable;
import main.java.exercise.helper.HashTableWithChaining;
import main.java.exercise.helper.Probe;
import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Jakob Johannes", // Vorname
                "Bauer", // Nachname
                "12002215" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für Verkettung der Überläufer
    public void insertVerkettung(HashTableWithChaining t, ChainElement chainElement, int m) {

    }

    // Implementieren Sie hier Ihre Lösung für die lineare Sondierung
    public int linearesSondieren(int key, int i, int m) {
        return 0;
    }

    // Implementieren Sie hier Ihre Lösung für die quadratische Sondierung
    public int quadratischesSondieren(int key, int i, int m) {
        return 0;
    }

    // Implementieren Sie hier Ihre Lösung für Double Hashing
    public int doubleHashing(int key, int i, int m) {
        return 0;
    }

    // Implementieren Sie hier Ihre Lösung für die Insert-Methode
    public void insert(HashTable t, Probe p, int key, int m) {

    }

    // Implementieren Sie hier Ihre Lösung für Verbesserung nach Brent
    public void insertVerbesserungNachBrent(HashTable t, int key, int m) {

    }

}
