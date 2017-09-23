package org.deeaae.learnings.aerospike.repository;

public interface Repository <T,K> {
    T save(T t);
    T get(K k, Class<T> type);
    T delete(K k, Class<T> type);
}
