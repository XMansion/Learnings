package org.deeaae.learnings.aerospike.repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import org.deeaae.learnings.aerospike.client.AeroClient;
import org.deeaae.learnings.aerospike.records.AeroBin;
import org.deeaae.learnings.aerospike.records.AeroKey;
import org.deeaae.learnings.aerospike.records.AeroRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AerospikeRepository<T extends AeroRecord,K extends AeroKey> implements Repository <T,K> {

    AerospikeClient client;

    public AerospikeRepository() {
        AeroClient aeroClient = new AeroClient();
        client =aeroClient.createClient();
        postClientSetup();

    }

    private void postClientSetup() {
        client.readPolicyDefault.sendKey=true;
        client.writePolicyDefault.sendKey=true;
        client.scanPolicyDefault.sendKey=true;
    }

    public AerospikeRepository(AeroClient aeroClient) {
        client =aeroClient.createClient();
        postClientSetup();
    }

    public AerospikeRepository(AerospikeClient aerospikeClient) {this.client = aerospikeClient; }

    @Override
    public T save(T t)
    {
        save(null,t);
        return t;
    }

    public T save(WritePolicy writePolicy, T t) {
        List<Bin> bins = new
                ArrayList<>();
        Key key = splitRecordIntoKeyAndBins(t,bins);
        client.put(writePolicy,key,bins.toArray(new Bin[bins.size()]));
        return  t;
    }

    private Key getKey(final AeroKey aeroKey) {
        Key key = new Key(aeroKey.getNamespace(),aeroKey.getSet(),aeroKey.getKey());
        return key;
    }

    private Bin getBin(final AeroBin aeroBin) {
        Bin bin = new Bin(aeroBin.getName(),aeroBin.getValue());
        return bin;
    }

    private T getAeroRecord(Record record, K k, Class<T> type) throws IllegalAccessException, InstantiationException {
        T aeroRecord = type.newInstance();
        record.bins.forEach((key,value)-> {
            AeroBin aeroBin = new AeroBin();
            aeroBin.setName(key);
            aeroBin.setValue(value);
            aeroRecord.addBin(aeroBin);
        });
        aeroRecord.setKey(k);
        return aeroRecord;
    }

    private Key splitRecordIntoKeyAndBins(AeroRecord record, List<Bin> bins) {
        AeroKey aeroKey = record.getKey();
        Key key = getKey(aeroKey);
        bins.addAll(
                record.getBins()
                .stream()
                .map(aeroBin-> getBin(aeroBin))
                .collect(Collectors.toList())
        );
        return key;
    }

    @Override
    public T get(K k, Class<T> type) {

        Key key = getKey(k);
        Record record = client.get(null,key);
        try {
            return getAeroRecord(record,k,type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error Reading",e);
        }
    }


    @Override
    public T delete(K k, Class<T> type)
    {
        T t = get(k,type);
        Key key = getKey(k);
        client.delete(null,key);
        return t;
    }
}
