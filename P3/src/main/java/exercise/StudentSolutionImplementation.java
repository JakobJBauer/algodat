package main.java.exercise;

import main.java.exercise.tree.AVLNode;
import main.java.exercise.tree.Node;
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

    // Implementieren Sie hier Ihre Lösung für das Einfügen in einen einfachen binären Suchbaum
    public void insertSimpleBinarySearchTree(Node root, Node newNode) {
        Node currentNode = root;
        while (currentNode.getKey() != newNode.getKey()) {
            if (newNode.getKey() > currentNode.getKey()) {
                if (currentNode.getRightChild() == null) currentNode.attachNodeRight(newNode);
                currentNode = currentNode.getRightChild();
            }
            if (newNode.getKey() < currentNode.getKey()) {
                if (currentNode.getLeftChild() == null) currentNode.attachNodeLeft(newNode);
                currentNode = currentNode.getLeftChild();
            }
        }
    }
    public void insertSimpleBinarySearchTreeRecursive(Node root, Node newNode) {
        if (newNode.getKey() > root.getKey()) {
            if (root.getRightChild() == null) root.attachNodeRight(newNode);
            else this.insertSimpleBinarySearchTreeRecursive(root.getRightChild(), newNode);
        }
        if (newNode.getKey() < root.getKey()) {
            if (root.getLeftChild() == null) root.attachNodeLeft(newNode);
            else this.insertSimpleBinarySearchTreeRecursive(root.getLeftChild(), newNode);
        }
    }

    // Implementieren Sie hier Ihre Lösung für das Einfügen in einen AVL-Baum
    public void insertAVLTree(AVLNode p, AVLNode newNode) {
        if (newNode.getKey() > p.getKey()) {
            if (p.getRightChild() == null) p.attachNodeRight(newNode);
            else this.insertAVLTree(p.getRightChild(), newNode);

            if (AVLNode.height(p.getRightChild()) - AVLNode.height(p.getLeftChild()) == 2) {
                if (AVLNode.height(p.getRightChild().getRightChild()) >= AVLNode.height(p.getRightChild().getLeftChild())) p.rotateToLeft();
                else p.doubleRotateRightLeft();
            }
        }
        if (newNode.getKey() < p.getKey()) {
            if (p.getLeftChild() == null) p.attachNodeLeft(newNode);
            else this.insertAVLTree(p.getLeftChild(), newNode);

            if (AVLNode.height(p.getRightChild()) - AVLNode.height(p.getLeftChild()) == -2) {
                if (AVLNode.height(p.getLeftChild().getLeftChild()) >= AVLNode.height(p.getLeftChild().getRightChild())) p.rotateToRight();
                else p.doubleRotateLeftRight();
            }
        }
        p.setHeight(Math.max(AVLNode.height(p.getLeftChild()), AVLNode.height(p.getRightChild())) + 1);
    }
}
