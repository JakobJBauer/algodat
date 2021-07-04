package main.java.exercise;

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

    /*
    *Implementieren Sie die Indexsuche in der Methode int findIndex(int[]
    numbers, int element).
    Mittels des Parameters int[] numbers wird ein Array mit Zahlen
    ubergeben, in dem nach dem Parameter int element gesucht werden soll.
    Der Ruckgabewert von int findIndex(int[] numbers, int element)
    soll jene Stelle (also jener Index) sein, an der int element erstmals in
    int[] numbers auftritt. Ist das gesuchte Element nicht enthalten, so geben
    Sie 􀀀1 zuruck.
    Beispiel: Wird im Array [4, 1, 0, 2] nach dem Element 0 gesucht, so soll
    2 zuruckgegeben werden, da 0 an Stelle 2 steht. Wird nach 6 gesucht, soll 􀀀1
    zuruckgegeben werden, da 6 nicht im Array enthalten ist.
    */

    // Implementieren Sie hier Ihre Lösung für die Indexsuche
    public int findIndex(int[] numbers, int element) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == element) return i;
        }
        return -1;
    }

    // Implementieren Sie hier den Gale-Shapley-Algorithmus
    public void performGaleShapley(StableMatchingInstance instance, StableMatchingSolution solution) {
        // Kennzeichne jedes Kind als frei (und dadurch auch jede Familie)
        for (int i = 0; i < instance.getN(); i++) {
            solution.setFree(i);
        }

        // Weise allen Kindern eine Familie zu
        while (solution.hasUnassignedChildren()) {
            int currChild = solution.getNextUnassignedChild();
            // Gehe jede Mögliche Kombination durch, wenn ein Matching stattfand, wird die Schleife abgebrochen
            // Andere Möglichkeit (wahrscheinlich erwünscht): Keine Schleife, sondern über Hashmap speicher welche Familien bereits probiert wurden
            for (int i = 0; i < instance.getN(); i++) {
                int currFam = instance.getFamilyOfChildAtRank(currChild, i);

                if (solution.isFamilyFree(currFam)){
                    solution.assign(currChild, currFam);
                    break;
                }
                if (instance.getRankOfChildForFamily(currFam, currChild) <
                        instance.getRankOfChildForFamily(
                                currFam,
                                solution.getAssignedChild(currFam))
                ) {
                    solution.setFree(solution.getAssignedChild(currFam));
                    solution.assign(currChild, currFam);
                    break;
                }
            }
        }
    }

    /*
    Implementieren Sie in der Methode
    boolean isStableMatching(StableMatchingInstance instance,
    StableMatchingSolution solution) einen Algorithmus, der fur die
    Instanz StableMatchingInstance instance bestimmt, ob das Matching
    in der Losung StableMatchingSolution solution stabil ist.
    StableMatchingSolution solution beinhaltet bereits ein perfektes
    Matching, das Sie nicht verandern sollen.
     */
    // Implementieren Sie hier Ihre Methode zur Überprüfung, ob ein Matching stabil ist.
    public boolean isStableMatching(StableMatchingInstance instance, StableMatchingSolution solution) {
        // gehe alle paare durch
        for (int currChild = 0; currChild < solution.getN(); currChild++) {
            // try if this match is unstable, but looking if any Family that currChild would prefer, would prefer currChild
            // to its assigned Child
            for (int rank = 0; rank < solution.getN(); rank++) {
                int preferredFam = instance.getFamilyOfChildAtRank(currChild, rank);

                // If it is already assigned, all is good
                if (preferredFam == solution.getAssignedFamily(currChild)) break;
                // Check if preferred family would prefer currChild to assigned Child, then we have a unstable match
                if (instance.getRankOfChildForFamily(preferredFam, currChild) <
                        instance.getRankOfChildForFamily(
                                preferredFam,
                                solution.getAssignedChild(preferredFam)
                        )){
                    return false;
                }
            }
        }
        return true;
    }
}
