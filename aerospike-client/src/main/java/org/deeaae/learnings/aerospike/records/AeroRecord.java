package org.deeaae.learnings.aerospike.records;

import java.util.ArrayList;
import java.util.List;

public class AeroRecord {
    AeroKey key;
    List<AeroBin> bins;

    public AeroRecord() {
        bins = new ArrayList<>();
    }

    public AeroKey getKey() {
        return key;
    }

    public void setKey(AeroKey key) {
        this.key = key;
    }

    public List<AeroBin> getBins() {
        return bins;
    }

    public void addBin(AeroBin bin) {
        bins.add(bin);
    }
}
