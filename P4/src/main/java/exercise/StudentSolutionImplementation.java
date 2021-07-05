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
        int position = this.h1(chainElement.getKey(), m);
        if (t.containsNoChainElement(position))
            t.insertChainElement(chainElement, position);
        else {
            ChainElement currLast = t.get(position);
            chainElement.setNext(currLast);
            t.replaceChainElement(chainElement, position);
        }
    }

    // Implementieren Sie hier Ihre Lösung für die lineare Sondierung
    public int linearesSondieren(int key, int i, int m) {
        return (h1(key, m) + i) % m;
    }

    // Implementieren Sie hier Ihre Lösung für die quadratische Sondierung
    public int quadratischesSondieren(int key, int i, int m) {
        return (int)(h1(key, m) + 0.5*i + 0.5*i*i) % m;
    }

    // Implementieren Sie hier Ihre Lösung für Double Hashing
    public int doubleHashing(int key, int i, int m) {
        return (h1(key, m) + i * h2(key)) % m;
    }

    // Implementieren Sie hier Ihre Lösung für die Insert-Methode
    public void insert(HashTable t, Probe p, int key, int m) {
        for (int i = 0; ; i++){
            int position = p.evaluate(key, i);
            if (t.isFree(position)) {
                t.insert(key, position);
                break;
            }
        }
    }

    // Implementieren Sie hier Ihre Lösung für Verbesserung nach Brent
    public void insertVerbesserungNachBrent(HashTable t, int key, int m) {
        int position = h1(key, m);
        while (!t.isFree(position)) {
            int key2 = t.get(position);
            int position1 = (position + h2(key)) % m;
            int position2 = (position + h2(key2)) % m;
            if (t.isFree(position1) || !t.isFree(position2))
                position = position1;
            else {
                t.replace(key, position);
                key = key2;
                position = position2;
            }
        }
        t.insert(key, position);
    }

    private int h1(int key, int m) {
        return key % m;
    }

    private int h2(int key) {
        return 1 + (key % 5);
    }

}
