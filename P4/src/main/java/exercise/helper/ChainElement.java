package main.java.exercise.helper;

import java.util.Objects;

public class ChainElement {

    private int key;
    private ChainElement next = null;

    public ChainElement(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public ChainElement getNext() {
        return next;
    }

    public void setNext(ChainElement chainElement) {
        this.next = chainElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChainElement that = (ChainElement) o;
        if (next == null && that.next != null) return false;
        return key == that.key && (next == null || next.equals(that.next));
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, next);
    }
}
