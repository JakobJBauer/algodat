package main.java.exercise;

import main.java.exercise.graph.Graph;
import main.java.exercise.helper.Heuristic;
import main.java.exercise.helper.Point;
import main.java.exercise.helper.PriorityQueue;
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

    // Implementieren Sie hier Ihre Lösung für die euklidische Distanz
    public double heuristicManhattanDistance(Point point1, Point point2) {
        return Math.abs(point2.getX() - point1.getX()) + Math.abs((point2.getY() - point1.getY()));
    }

    // Implementieren Sie hier Ihre Lösung für die euklidische Distanz
    public double heuristicEuclideanDistance(Point point1, Point point2) {
        double deltaX = point2.getX() - point1.getX();
        double deltaY = point2.getY() - point1.getY();
        return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    }

    // Implementieren Sie hier Ihre Lösung für A*
    public void aStar(Graph g, PriorityQueue q, Heuristic h, int source, int target, int[] path) {
        double[] weights = new double[g.numberOfVertices()];
        for (int i = 0; i < g.numberOfVertices(); i++) {
            weights[i] = Double.POSITIVE_INFINITY;
        }
        weights[source-1] = 0;

        int[] pred = new int[weights.length];

        q.add(source, h.evaluate(source));

        while (!q.isEmpty()) {
            int x = q.removeFirst();
            if (x == target) {
                this.maskIntoPath(pred, path, x);
                //this.maskIntoPath(pred, path);
                return;
            }
            for (int successor: g.getSuccessors(x)) {
                double candidate = weights[x-1] + g.getEdgeWeight(x, successor);
                if (candidate < weights[successor-1]) {
                    weights[successor-1] = candidate;
                    pred[successor-1] = x; // Better path after x
                    if (q.contains(successor)) q.decreaseWeight(successor, candidate + h.evaluate(successor));
                    else q.add(successor, candidate + h.evaluate(successor));
                }
            }
        }
    }
    
    private void maskIntoPath(int[] predecessors, int[] path, int node) {
        for (int i = path.length - 1; i >= 0 && node != 0; i--) {
            path[i] = node;
            node = predecessors[node - 1];
        }
    }
}
