package main.java.exercise;

import main.java.exercise.helper.InstanceType;
import main.java.exercise.helper.ProbeType;
import main.java.exercise.instace_implementation.CommonInstanceImplementation;
import main.java.exercise.instace_implementation.HashInstanceImplementation;
import main.java.exercise.instace_implementation.InsertInstanceImplementation;
import main.java.exercise.result_implementation.CommonResultImplementation;
import main.java.framework.InstanceSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class InstanceSetImplementation extends InstanceSet<CommonInstanceImplementation, StudentSolutionImplementation, CommonResultImplementation, VerifierImplementation, int[]> {

    public InstanceSetImplementation(Path instanceSetPath, Path outputPath) {
        super(instanceSetPath, outputPath, CommonResultImplementation.class);
    }

    @Override
    protected CommonInstanceImplementation instanceFromCsv(String line) {
        String[] splitLine = line.split(",");
        int number = Integer.parseInt(splitLine[0]);
        String groupName = splitLine[1];
        InstanceType type = InstanceType.getInstanceTypeByGroupName(groupName);
        int m = Integer.parseInt(splitLine[4]);
        switch (type) {
            case LINEARES_SONDIEREN:
            case QUADRATISCHES_SONDIEREN:
            case DOUBLE_HASHING:
                int key = Integer.parseInt(splitLine[2]);
                int i = Integer.parseInt(splitLine[3]);
                int expectedPosition = Integer.parseInt(splitLine[5]);
                return new HashInstanceImplementation(groupName, number, type, key, i, m, expectedPosition);
            case VERKETTUNG:
            case INSERT:
            case VERBESSERUNG_NACH_BRENT:
            default:
                int[] keys = this.getAdditionalInput(splitLine[2]);
                int[] unusedKeys = this.getAdditionalInput("keys-unused.txt");
                int numberOfKeys = Integer.parseInt(splitLine[3]);
                ProbeType probeType = ProbeType.getProbeTypeByName(splitLine[5]);
                String[] expectedResult = splitLine[6].split("\\|", -1);
                return new InsertInstanceImplementation(groupName, number, type, keys, numberOfKeys, m, probeType, expectedResult, unusedKeys);
        }
    }

    @Override
    protected StudentSolutionImplementation provideStudentSolution() {
        return new StudentSolutionImplementation();
    }

    @Override
    protected VerifierImplementation provideVerifier() {
        return new VerifierImplementation();
    }

    @Override
    protected int[] parseAdditionalInput(BufferedReader reader) {
        List<String> keys = new ArrayList<String>();
        try {
            String key;
            while ((key = reader.readLine()) != null) {
                keys.add(key);
            }
        } catch (IOException e) {
            return new int[0];
        }
        String[] keyArray = keys.toArray(new String[keys.size()]);
        int[] parsedKeys = new int[keyArray.length];
        for (int i = 0; i < keys.size(); i++) {
            parsedKeys[i] = Integer.parseInt(keyArray[i]);
        }
        return parsedKeys;
    }

}
