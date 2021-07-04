package main.java.exercise.helper;

public class HashTableWithChaining {

    private ChainElement[] table;

    public HashTableWithChaining(ChainElement[] table) {
        this.table = table;
    }

    public boolean containsNoChainElement(int position) {
        if (position >= this.table.length) {
            throw new IndexOutOfBoundsException("Index " + position + " out of bounds. Size of hashtable is " + this.table.length + ".");
        }
        if (position < 0) {
            throw new IndexOutOfBoundsException("Index " + position + " out of bounds.");
        }
        if (this.table[position] != null) {
            return false;
        } else {
            return true;
        }
    }

    public ChainElement get(int position) {
        if (!this.containsNoChainElement(position)) {
            return this.table[position];
        } else {
            throw new IllegalArgumentException("Cannot fetch chain element at index " + position + ", since the index does not contain one.");
        }
    }

    public void insertChainElement(ChainElement chainElement, int position) {
        if (this.containsNoChainElement(position)) {
            this.table[position] = chainElement;
        } else {
            throw new IllegalArgumentException("Cannot insert chain element with key " + chainElement.getKey() + " at index " + position + ", since the index already contains one.");
        }
    }

    public void replaceChainElement(ChainElement chainElement, int position) {
        if (!this.containsNoChainElement(position)) {
            this.table[position] = chainElement;
        } else {
            throw new IllegalArgumentException("Cannot replace chain element at index " + position + " since the index does not contain one.");
        }
    }
}
