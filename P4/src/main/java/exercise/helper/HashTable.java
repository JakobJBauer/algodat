package main.java.exercise.helper;

public class HashTable {

    private int[] table;

    public HashTable(int[] table) {
        this.table = table;
    }

    public boolean isFree(int position) {
        if (position >= this.table.length) {
            throw new IndexOutOfBoundsException("Index " + position + " out of bounds. Size of hashtable is " + this.table.length + ".");
        }
        if (position < 0) {
            throw new IndexOutOfBoundsException("Index " + position + " out of bounds.");
        }
        if (this.table[position] > 0) {
            return false;
        } else {
            return true;
        }
    }

    public int get(int position) {
        if (!this.isFree(position)) {
            return this.table[position];
        } else {
            throw new IllegalArgumentException("Cannot fetch key at index " + position + " since the index is free.");
        }
    }

    public void insert(int key, int position) {
        if (key <= 0) {
            throw new IllegalArgumentException("Cannot insert key " + key + ", since keys have to be larger than 0");
        }
        if (this.isFree(position)) {
            this.table[position] = key;
        } else {
            throw new IllegalArgumentException("Cannot insert key " + key + " at index " + position + ", since the index is not free.");
        }
    }

    public void replace(int key, int position) {
        if (key <= 0) {
            throw new IllegalArgumentException("Cannot replace key " + key + ", since keys have to be larger than 0");
        }
        if (!this.isFree(position)) {
            this.table[position] = key;
        } else {
            throw new IllegalArgumentException("Cannot replace key at index " + position + " since the index is free.");
        }
    }

}
