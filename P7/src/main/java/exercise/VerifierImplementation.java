package main.java.exercise;

import main.java.framework.Report;
import main.java.framework.Timer;
import main.java.framework.Verifier;

public class VerifierImplementation extends Verifier<InstanceImplementation, StudentSolutionImplementation, ResultImplementation> {

    @Override
    public ResultImplementation solveProblemUsingStudentSolution(InstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        if (instance.getGroupName().equals("Feasible Negativ") || instance.getGroupName().equals("Feasible Positiv")) {
            Timer timer = new Timer();
            timer.start();
            boolean solutionIsFeasible = studentSolution.isFeasible(instance.getCLCSInstance(), instance.getSolutionCandit());
            timer.stop();
            return new ResultImplementation(instance.getGroupName(), timer.getDuration(), instance.getSize(),
                    instance.getCLCSInstance().getNp(), instance.getCLCSInstance().alphabet.size(),
                    null,
                    solutionIsFeasible, null, null, instance.getCLCSInstance().toStringOneLine());
        } else {
            Timer timerTabelle = new Timer();
            timerTabelle.start();
            instance.getCLCSInstance().computeDynamicProgrammingTable();
            timerTabelle.stop();

            Timer timerCLCS = new Timer();
            Timer timerLCS = new Timer();

            char[] solutionCLCS = null;
            char[] solutionLCS = null;

            if (instance.getGroupName().equals("CLCS") || instance.getGroupName().equals("CLCS and LCS")) {
                timerCLCS.start();
                try {
                    solutionCLCS = instance.getCLCSInstance().backtrackingCLCS();
                }
                catch (CLCSInstance.EmptyCharException e) {
                    e.printStackTrace();
                }

                timerCLCS.stop();
            }

            if (instance.getGroupName().equals("LCS") || instance.getGroupName().equals("CLCS and LCS")) {
                timerLCS.start();
                try {
                    solutionLCS = instance.getCLCSInstance().backtrackingLCS();
                }
                catch (CLCSInstance.EmptyCharException e) {
                    e.printStackTrace();
                }
                timerLCS.stop();
            }

            long time = timerTabelle.getDuration() + timerCLCS.getDuration() + timerLCS.getDuration();
            //if (!instance.getGroupName().contains("CLCS") || !instance.getGroupName().contains("LCS")) {
            //    time = -1;
            //}

            return new ResultImplementation(instance.getGroupName(), time, instance.getSize(),
                    instance.getCLCSInstance().getNp(), instance.getCLCSInstance().alphabet.size(),
                    instance.getCLCSInstance().getTabelle().toStringOneLine(),
                    false, solutionCLCS, solutionLCS, instance.getCLCSInstance().toStringOneLine());
        }
    }

    @Override
    public Report verifyResult(InstanceImplementation instance, ResultImplementation result) {
        if (instance.getGroupName().equals("Feasible Positiv") || instance.getGroupName().equals("Feasible Negativ")) {
            if (instance.getSolutionIsFeasible() == result.getSolutionIsFeasible()) {
                return new Report(true, "");
            } else {
                return new Report(false, "Error in instance " + instance.getNumber() + ": Test for Feasibility expected to be " + instance.getSolutionIsFeasible() + " but was " + result.getSolutionIsFeasible() + ".");
            }
        } else {
            if (instance.getGroupName().equals("CLCS")) {
                if (!instance.getCLCSInstance().isFeasible(result.getSolutionCLCS())) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": CLCS Solution might be infeasible.");
                }
                if (instance.getOptimumCLCS() != result.getSolutionCLCS().length) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Length of optimal CLCS Solution expected to be\n" + instance.getOptimumCLCS() + " but was\n" + result.getSolutionCLCS().length + ".");
                }
            }
            if (instance.getGroupName().equals("CLCS and LCS")) {
                if (!instance.getCLCSInstance().isFeasible(result.getSolutionCLCS())) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": CLCS Solution might be infeasible.");
                }
                if (instance.getOptimumLCS() != result.getSolutionLCS().length) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Length of optimal LCS Solution expected to be\n" + instance.getOptimumLCS() + " but was\n" + result.getSolutionLCS().length + ".");
                }
                if (instance.getOptimumCLCS() != result.getSolutionCLCS().length) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Length of optimal CLCS Solution expected to be\n" + instance.getOptimumCLCS() + " but was\n" + result.getSolutionCLCS().length + ".");
                }
            }
            if (instance.getGroupName().equals("LCS"))  { // LCS
                if (!instance.getCLCSInstance().isFeasibleLCS(result.getSolutionLCS())) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": LCS Solution is not infeasible.");
                }
                if (instance.getOptimumLCS() != result.getSolutionLCS().length) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Length of optimal LCS Solution expected to be\n" + instance.getOptimumLCS() + " but was\n" + result.getSolutionLCS().length + ".");
                }
            }
            return new Report(true, "");
        }
    }
}
