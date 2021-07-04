package main.java.exercise;

import main.java.exercise.helper.*;
import main.java.exercise.instace_implementation.CommonInstanceImplementation;
import main.java.exercise.instace_implementation.HashInstanceImplementation;
import main.java.exercise.instace_implementation.InsertInstanceImplementation;
import main.java.exercise.result_implementation.CommonResultImplementation;
import main.java.exercise.result_implementation.HashResultImplementation;
import main.java.exercise.result_implementation.InsertChainedResultImplementation;
import main.java.exercise.result_implementation.InsertResultImplementation;
import main.java.framework.Report;
import main.java.framework.Verifier;

import java.util.ArrayList;

public class VerifierImplementation extends Verifier<CommonInstanceImplementation, StudentSolutionImplementation, CommonResultImplementation> {

    String s = "";

    @Override
    public CommonResultImplementation solveProblemUsingStudentSolution(CommonInstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        switch (instance.getInstanceType()) {
            // hash function checks
            case LINEARES_SONDIEREN:
            case QUADRATISCHES_SONDIEREN:
            case DOUBLE_HASHING:
                HashInstanceImplementation hashInstanceImplementation = (HashInstanceImplementation) instance;
                int key = hashInstanceImplementation.getKey();
                int i = hashInstanceImplementation.getI();
                int m1 = hashInstanceImplementation.getM();
                int position = -1;
                switch (instance.getInstanceType()) {
                    case LINEARES_SONDIEREN:
                        position = studentSolution.linearesSondieren(key, i, m1);
                        break;
                    case QUADRATISCHES_SONDIEREN:
                        position = studentSolution.quadratischesSondieren(key, i, m1);
                        break;
                    case DOUBLE_HASHING:
                        position = studentSolution.doubleHashing(key, i, m1);
                        break;
                }
                return new HashResultImplementation(instance.getInstanceType(), position);
            // insertion checks
            case VERKETTUNG:
            case INSERT:
            case VERBESSERUNG_NACH_BRENT:
                InsertInstanceImplementation insertInstanceImplementation = (InsertInstanceImplementation) instance;
                int[] keys = insertInstanceImplementation.getKeys();
                int numberOfKeys = insertInstanceImplementation.getNumberOfKeys();
                int m2 = insertInstanceImplementation.getM();
                int[] unusedKeys = insertInstanceImplementation.getUnusedKeys();
                if (instance.getInstanceType() == InstanceType.VERKETTUNG) {
                    // insertion with chaining
                    ChainElement[] table = new ChainElement[m2];
                    HashTableWithChaining tChained = new HashTableWithChaining(table);
                    for (int j = 0; j < numberOfKeys && j < m2; j++) {
                        studentSolution.insertVerkettung(tChained, new ChainElement(keys[j]), m2);
                    }
                    double stepsAvg = 0;
                    for (int k = 0; k < numberOfKeys; k++) {
                        int steps = this.lookupVerkettung(table, keys[k], m2);
                        double stepsOverall = stepsAvg * k;
                        stepsOverall = stepsOverall + steps;
                        stepsAvg = stepsOverall / (k + 1);
                    }
                    double stepsNegativeAvg = 0;
                    for (int k = 0; k < unusedKeys.length; k++) {
                        int steps = this.lookupVerkettung(table, unusedKeys[k], m2);
                        double stepsNegativeOverall = stepsNegativeAvg * k;
                        stepsNegativeOverall = stepsNegativeOverall + steps;
                        stepsNegativeAvg = stepsNegativeOverall / (k + 1);
                    }
                    return new InsertChainedResultImplementation(instance.getInstanceType(), insertInstanceImplementation.getProbeType(), table, stepsAvg, m2, numberOfKeys, stepsNegativeAvg);
                } else if (instance.getInstanceType() == InstanceType.INSERT) {
                    // standard insertion
                    int[] table = new int[m2];
                    HashTable t = new HashTable(table);
                    Probe p = new Probe(studentSolution, insertInstanceImplementation.getProbeType(), m2);
                    for (int j = 0; j < numberOfKeys && j < m2; j++) {
                        studentSolution.insert(t, p, keys[j], m2);
                    }
                    double stepsAvg = 0;
                    for (int k = 0; k < numberOfKeys; k++) {
                        int steps = this.lookupInsert(table, p, keys[k]);
                        double stepsOverall = stepsAvg * k;
                        stepsOverall = stepsOverall + steps;
                        stepsAvg = stepsOverall / (k + 1);
                    }
                    double stepsNegativeAvg = 0;
                    for (int k = 0; k < unusedKeys.length; k++) {
                        int steps = this.lookupInsert(table, p, unusedKeys[k]);
                        double stepsNegativeOverall = stepsNegativeAvg * k;
                        stepsNegativeOverall = stepsNegativeOverall + steps;
                        stepsNegativeAvg = stepsNegativeOverall / (k + 1);
                    }
                    return new InsertResultImplementation(instance.getInstanceType(), insertInstanceImplementation.getProbeType(), table, stepsAvg, m2, numberOfKeys, stepsNegativeAvg);
                } else if (instance.getInstanceType() == InstanceType.VERBESSERUNG_NACH_BRENT) {
                    // insertion Verbesserung
                    int[] table = new int[m2];
                    HashTable t = new HashTable(table);
                    for (int j = 0; j < numberOfKeys && j < m2; j++) {
                        studentSolution.insertVerbesserungNachBrent(t, keys[j], m2);
                    }
                    double stepsAvg = 0;
                    for (int k = 0; k < numberOfKeys; k++) {
                        int steps = this.lookupVerbesserungNachBrent(table, keys[k], m2);
                        double stepsOverall = stepsAvg * k;
                        stepsOverall = stepsOverall + steps;
                        stepsAvg = stepsOverall / (k + 1);
                    }
                    double stepsNegativeAvg = 0;
                    for (int k = 0; k < unusedKeys.length; k++) {
                        int steps = this.lookupVerbesserungNachBrent(table, unusedKeys[k], m2);
                        double stepsNegativeOverall = stepsNegativeAvg * k;
                        stepsNegativeOverall = stepsNegativeOverall + steps;
                        stepsNegativeAvg = stepsNegativeOverall / (k + 1);
                    }
                    return new InsertResultImplementation(instance.getInstanceType(), insertInstanceImplementation.getProbeType(), table, stepsAvg, m2, numberOfKeys, stepsNegativeAvg);
                }
        }
        return null;
    }

    private int lookupVerkettung(ChainElement[] t, int key, int m) {
        int steps = 0;
        ChainElement foundKey = t[key % m];
        while (foundKey != null) {
            steps++;
            if (foundKey.getKey() != key) {
                foundKey = foundKey.getNext();
            } else {
                break;
            }
        }
        return steps;
    }

    private int lookupInsert(int[] t, Probe p, int key) {
        int steps = 0;
        for (int i = 0; i < t.length; i++) {
            int foundKey = t[p.evaluate(key, i)];
            steps++;
            if (foundKey == key || foundKey == 0) {
                break;
            }
        }
        return steps;
    }

    private int lookupVerbesserungNachBrent(int[] t, int key, int m) {
        int steps = 0;
        for (int i = 0; i < t.length; i++) {
            int foundKey = t[((key % m) + i * ((key % 5) + 1)) % m];
            steps++;
            if (foundKey == key || foundKey == 0) {
                break;
            }
        }
        return steps;
    }

    @Override
    public Report verifyResult(CommonInstanceImplementation instance, CommonResultImplementation result) {
        switch (instance.getInstanceType()) {
            case LINEARES_SONDIEREN:
            case QUADRATISCHES_SONDIEREN:
            case DOUBLE_HASHING:
                // verify default check
                HashInstanceImplementation hashInstanceImplementation = (HashInstanceImplementation) instance;
                HashResultImplementation hashResultImplementation = (HashResultImplementation) result;
                if (hashInstanceImplementation.getExpectedPosition() != hashResultImplementation.getPosition()) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": "
                            + instance.getInstanceType()
                            + " with key = " + hashInstanceImplementation.getKey()
                            + ", i = " + hashInstanceImplementation.getI()
                            + " and m = " + hashInstanceImplementation.getM()
                            + " expects position " + hashInstanceImplementation.getExpectedPosition()
                            + ", but " + hashResultImplementation.getPosition() + " was returned.");
                }
                break;
            case VERKETTUNG:
            case INSERT:
            case VERBESSERUNG_NACH_BRENT:
                InsertInstanceImplementation insertInstanceImplementation = (InsertInstanceImplementation) instance;
                String[] expectedResultString = insertInstanceImplementation.getExpectedResult();

                if (instance.getInstanceType() == InstanceType.VERKETTUNG) {
                    // verify insertion with chaining
                    InsertChainedResultImplementation insertChainedResultImplementation = (InsertChainedResultImplementation) result;

                    // parse expected result table
                    ChainElement[] expectedResult = new ChainElement[expectedResultString.length];
                    for (int i = 0; i < expectedResultString.length && i < expectedResult.length; i++) {
                        String[] keys = expectedResultString[i].split("<");
                        if (keys.length > 0 && !keys[0].equals("")) {
                            ChainElement current = new ChainElement(Integer.parseInt(keys[0]));
                            expectedResult[i] = current;
                            for (int j = 1; j < keys.length; j++) {
                                ChainElement next = new ChainElement(Integer.parseInt(keys[j]));
                                current.setNext(next);
                                current = next;
                            }
                        }
                    }

                    // compare tables
                    ChainElement[] resultTable = insertChainedResultImplementation.getResult();
                    for (int i = 0; i < expectedResult.length && i < resultTable.length; i++) {
                        ChainElement expectedChainElement = expectedResult[i];
                        ChainElement chainElement = resultTable[i];
                        if (expectedChainElement == null && chainElement == null) {
                            continue;
                        } else if (expectedChainElement == null || chainElement == null) {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": "
                                    + "Resulting hash table does not match expected hash table using"
                                    + " " + insertInstanceImplementation.getInstanceType()
                                    + ".\nFor reference, check the following log of the hash table.\n\n" + this.printHashTableWithChaining(expectedResult, resultTable));
                        } else {
                            if (!expectedChainElement.equals(chainElement)) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": "
                                        + "Resulting hash table does not match expected hash table using"
                                        + " " + insertInstanceImplementation.getInstanceType()
                                        + ".\nFor reference, check the following log of the hash table.\n\n" + this.printHashTableWithChaining(expectedResult, resultTable));                            }
                        }
                    }
                    if (insertInstanceImplementation.getNumber() == 18) {
                        System.out.println(s);
                    }
                    //break;
                } else if (
                        instance.getInstanceType() == InstanceType.INSERT ||
                                instance.getInstanceType() == InstanceType.VERBESSERUNG_NACH_BRENT
                ) {
                    // verify standard insertion and verbesserung
                    InsertResultImplementation insertResultImplementation = (InsertResultImplementation) result;

                    // parse expected result table
                    int[] expectedResult = new int[expectedResultString.length];
                    for (int j = 0; j < expectedResultString.length && j < expectedResult.length; j++) {
                        expectedResult[j] = Integer.parseInt(expectedResultString[j]);
                    }

                    int[] keys = insertInstanceImplementation.getKeys();

                    // compare tables
                    int[] resultTable = insertResultImplementation.getResult();
                    ArrayList<Integer> incorrectKeys = new ArrayList<>();
                    for (int i = 0; i < expectedResult.length || i < resultTable.length; i++) {
                        if (expectedResult[i] != resultTable[i]) {
                            if (expectedResult[i] != 0) {
                                incorrectKeys.add(expectedResult[i]);
                            }
                            if (resultTable[i] != 0) {
                                incorrectKeys.add(resultTable[i]);
                                boolean found = false;
                                for (int j = 0; j < keys.length; j++) {
                                    if (keys[j] == resultTable[i]) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    return new Report(false, "Error in instance " + instance.getNumber() + ": "
                                            + "Unknown key " + resultTable[i] +  " has been inserted into the hash table using"
                                            + (insertInstanceImplementation.getInstanceType() == InstanceType.VERBESSERUNG_NACH_BRENT
                                            ? " " + insertInstanceImplementation.getInstanceType()
                                            : " " + insertInstanceImplementation.getProbeType())
                                            + ".\nFor reference, check the following log of the hash table.\n\n" + this.printHashTable(expectedResult, resultTable, insertInstanceImplementation.getKeys(), -1));
                                }
                            }
                        }
                    }

                    // output table when first key was wrongly inserted
                    for (int i = 0; i < keys.length; i++) {
                        if (incorrectKeys.contains(keys[i])) {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": "
                                    + "Key " + keys[i]
                                    + " (key number " + (i + 1) + " out of " + insertInstanceImplementation.getNumberOfKeys() + ")"
                                    + " was inserted at the wrong position (or not inserted at all) using"
                                    + (insertInstanceImplementation.getInstanceType() == InstanceType.VERBESSERUNG_NACH_BRENT
                                        ? " " + insertInstanceImplementation.getInstanceType()
                                        : " " + insertInstanceImplementation.getProbeType())
                                    + ".\nFor reference, check the following log of the hash table.\n\n" + this.printHashTable(expectedResult, resultTable, insertInstanceImplementation.getKeys(), i));
                        }
                    }
                }
                break;

        }
        return new Report(true, "");
    }

    private String printHashTableWithChaining(ChainElement[] expectedResult, ChainElement[] resultTable) {
        String line = "";
        for (int i = 0; i < expectedResult.length && i < resultTable.length; i++) {
            ChainElement chainElement = resultTable[i];
            ChainElement expectedChainElement = expectedResult[i];

            String resultChain = "";
            String expectedChain = "";
            if (expectedChainElement == null && chainElement == null) {
                resultChain = "   ------   ";
                expectedChain = "   ------   ";
            } else if (expectedChainElement == null || chainElement == null) {
                if (chainElement == null) {
                    resultChain = "   ------   ";
                    expectedChain = this.printChain(expectedChainElement, true);
                }
                if (expectedChainElement == null) {
                    resultChain = this.printChain(chainElement, true);
                    expectedChain = "   ------   ";
                }
            } else {
                if (!expectedChainElement.equals(chainElement)) {

                    while (!(chainElement == null && expectedChainElement == null)) {
                        if (chainElement != null && expectedChainElement != null) {
                            if (chainElement.getKey() != expectedChainElement.getKey()) {
                                resultChain += String.format(" > %6d < |", chainElement.getKey());
                                expectedChain += String.format(" > %6d < |", expectedChainElement.getKey());
                            } else {
                                resultChain += String.format("   %6d   |", chainElement.getKey());
                                expectedChain += String.format("   %6d   |", expectedChainElement.getKey());
                            }
                            chainElement = chainElement.getNext();
                            expectedChainElement = expectedChainElement.getNext();
                        } else if (chainElement != null && expectedChainElement == null) {
                            resultChain += String.format(" > %6d < |", chainElement.getKey());
                            chainElement = chainElement.getNext();
                        } else if (chainElement == null && expectedChainElement != null) {
                            expectedChain += String.format(" > %6d < |", expectedChainElement.getKey());
                            expectedChainElement = expectedChainElement.getNext();
                        }
                    }
                } else {
                    resultChain = this.printChain(chainElement, false);
                    expectedChain = this.printChain(expectedChainElement, false);
                }
            }

            line += "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
            line += String.format("Position %6d |", i) + "\n";
            line += "Your Chain      |" + resultChain + "\n";
            line += "Expected Chain  |" + expectedChain + "\n";
        }
        line += "---------\n";
        return line;
    }

    private String printChain(ChainElement chain, boolean faulty) {
        if (faulty) {
            String chainString = "";
            while (chain != null) {
                chainString += String.format(" > %6d < |", chain.getKey());
                chain = chain.getNext();
            }
            return chainString;
        } else {
            String chainString = "";
            while (chain != null) {
                chainString += String.format("   %6d   |", chain.getKey());
                chain = chain.getNext();
            }
            return chainString;
        }
    }

    private boolean isInFirstNKeys(int[] keys, int n, int key) {
        if (n == -1) {
            return true;
        }
        for (int i = 0; i < keys.length && i <= n; i++) {
            if (keys[i] == key) {
                return true;
            }
        }
        return false;
    }

    private String printHashTable(int[] expectedResult, int[] resultTable, int[] keys, int upTo) {
        String line = "";
        for (int l = 0; l < ((int) (expectedResult.length / 10.0)); l++) {
            line += printHashTableRow(l * 10, l * 10 + 9, expectedResult, resultTable, keys, upTo);
        }
        if ((int) (expectedResult.length / 10.0) * 10 < expectedResult.length) {
            line += printHashTableRow(((int) (expectedResult.length / 10.0)) * 10, expectedResult.length - 1, expectedResult, resultTable, keys, upTo);
        }
        return line;
    }

    private String printHashTableRow(int from, int to, int[] expectedResult, int[] resultTable, int[] keys, int upTo) {
        String line = "";
        line += "Positions " + from + " - " + to + "\n";
        line += "Position:   ";
        for (int i = from; i <= to; i++) {
            line += "|   ";
            line += String.format("%6d", i);
            line += "   ";
        }
        line += "|\n";
        line += "Your Table: ";
        for (int i = from; i <= to; i++) {
            if (this.isInFirstNKeys(keys, upTo, resultTable[i]) && resultTable[i] != 0) {
                if (resultTable[i] == expectedResult[i]) {
                    line += "|   ";
                    line += String.format("%6d", resultTable[i]);
                    line += "   ";
                } else {
                    line += "| > ";
                    line += String.format("%6d", resultTable[i]);
                    line += " < ";
                }
            } else {
                line += "|   ";
                line += "------";
                line += "   ";
            }
        }
        line += "|\n";
        line += "Expected:   ";
        for (int i = from; i <= to; i++) {
            if (this.isInFirstNKeys(keys, upTo, expectedResult[i]) && expectedResult[i] != 0) {
                if (resultTable[i] == expectedResult[i]) {
                    line += "|   ";
                    line += String.format("%6d", expectedResult[i]);
                    line += "   ";
                } else {
                    line += "| > ";
                    line += String.format("%6d", expectedResult[i]);
                    line += " < ";
                }
            } else {
                line += "|   ";
                line += "------";
                line += "   ";
            }
        }
        line += "|\n";
        line += "\n";
        return line;
    }
}
