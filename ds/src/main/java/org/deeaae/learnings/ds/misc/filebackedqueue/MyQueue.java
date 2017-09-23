package org.deeaae.learnings.ds.misc.filebackedqueue;

public interface MyQueue<T> {
    T peek();
    T get();
    void push(T data);
}
