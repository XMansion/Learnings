package org.deeaae.learnings.aerospike.records;

public class AeroKey {
    String key;
    String namespace;
    String set;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
