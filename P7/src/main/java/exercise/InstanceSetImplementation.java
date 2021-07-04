package main.java.exercise;

import main.java.framework.InstanceSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class InstanceSetImplementation extends InstanceSet<InstanceImplementation, StudentSolutionImplementation, ResultImplementation, VerifierImplementation, Object> {

    private int[] sizes;                    // Problemgrößen
    private boolean bool;                   // Instanzen schon eingelesen?
    private CLCSInstance[] instanzen;       // Instanzen

    HashSet<Character> alphabet4 = new HashSet<Character>();
    HashSet<Character> alphabet20 = new HashSet<Character>();
    HashSet<Character> alphabet26 = new HashSet<Character>();

    public InstanceSetImplementation(Path instanceSetPath, Path outputPath) {
        super(instanceSetPath, outputPath, ResultImplementation.class);

        alphabet4 = new HashSet<Character>();
        char[] letters4 = "ACGT".toCharArray();
        for (int i = 0; i < letters4.length; i++) {
            alphabet4.add(letters4[i]);
        }

        alphabet20 = new HashSet<Character>();
        char[] letters20 = "ACDEFGHIKLMNPQRSTVWY".toCharArray();
        for (int i = 0; i < letters20.length; i++) {
            alphabet20.add(letters20[i]);
        }

        alphabet26 = new HashSet<Character>();
        char[] letters26 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < letters26.length; i++) {
            alphabet26.add(letters26[i]);
        }
    }

    @Override
    protected InstanceImplementation instanceFromCsv(String line) {
        String[] splitLine = line.split(",");
        String problemType = splitLine[1];

        if (!bool) {
            System.out.println("Read in Instances");
            sizes = readSizes();
            readInstances();
            bool = true;
        }
        int instanznr = Integer.parseInt(splitLine[2]);
        int size = sizes[instanznr];
        CLCSInstance instanz = instanzen[instanznr];

        if (problemType.equals("Feasible Positiv") || problemType.equals("Feasible Negativ")) {

            char[] solutionCandit = splitLine[3].toCharArray();
            boolean solIsFeasible = Boolean.parseBoolean(splitLine[4]);

            return new InstanceImplementation(splitLine[1], Integer.parseInt(splitLine[0]), instanz, size,
                    instanz.alphabet.size(), solutionCandit, solIsFeasible,-1, -1);
        } else {
            int optLCS = -1;
            int optCLCS = -1;

            if (problemType.contains("LCS")) {
                optLCS = Integer.parseInt(splitLine[4]);
            }
            if (problemType.contains("CLCS")) {
                optCLCS = Integer.parseInt(splitLine[3]);
            }

            return new InstanceImplementation(splitLine[1], Integer.parseInt(splitLine[0]), instanz, size,
                    instanz.alphabet.size(),null, false, optLCS, optCLCS);
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


    protected int[] readSizes() {
        Path pfad = FileSystems.getDefault().getPath("additional-input", "sizes.csv");
        BufferedReader br;
        try {
            br = Files.newBufferedReader(pfad);
            return(this.parseNumbers(br));
        } catch (IOException e) {
            System.err.println("Error while reading additional input");
        }
        return null;
    }


    protected int[] parseNumbers(BufferedReader reader) {
        List<Integer> numbers = new ArrayList<Integer>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            return null;
        }
        int[] res = new int[numbers.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = numbers.remove(0);
        }
        return res;
    }


    public void readInstances() {
        int anz = -1;

        Path pfad = FileSystems.getDefault().getPath("additional-input", "additionalInfoCLCS.csv");
        BufferedReader br;
        try {
            br = Files.newBufferedReader(pfad);

            instanzen = new CLCSInstance[sizes.length];

            for (int k = 0; k < instanzen.length; k++) {
                String[] splitLine = br.readLine().split(",");

                int nr = Integer.parseInt(splitLine[0]);
                int alphabetsize = Integer.parseInt(splitLine[2]);

                HashSet<Character> alphabet = null;
                if (alphabetsize == 4) {
                    alphabet = alphabet4;
                }
                else if (alphabetsize == 20) {
                    alphabet = alphabet20;
                }
                else {
                    alphabet = alphabet20;
                }

                char[] s1 = splitLine[3].toCharArray();
                char[] s2 = splitLine[4].toCharArray();
                char[] pattern = splitLine[5].toCharArray();

                if (nr != k) {
                    System.out.println("Achtung: nr != k ergibt true!");
                }

                instanzen[k] = new CLCSInstance(s1, s2, pattern, alphabet, provideStudentSolution());
            }
        }
        catch (IOException e) {

        }
    }

    @Override
    protected Integer[] parseAdditionalInput(BufferedReader reader) {
        return null;
    }
}
