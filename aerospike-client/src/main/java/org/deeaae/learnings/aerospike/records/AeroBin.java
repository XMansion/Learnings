package org.deeaae.learnings.aerospike.records;

public class AeroBin<T> {
    String name;
    T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
