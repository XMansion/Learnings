package org.deeaae.learnings.app.aeroclient;

import com.aerospike.client.policy.WritePolicy;
import org.deeaae.learnings.aerospike.client.AeroClient;
import org.deeaae.learnings.aerospike.records.AeroRecord;
import org.deeaae.learnings.aerospike.records.AeroBin;
import org.deeaae.learnings.aerospike.records.AeroKey;
import org.deeaae.learnings.aerospike.repository.AerospikeRepository;
import org.luaj.vm2.ast.Str;


import java.util.UUID;

public class ClientApp {
    AeroClient aeroClient;

    public void init() {
        aeroClient = new AeroClient();
        aeroClient.createClient();
    }

    public void writeData() {
        AerospikeRepository<AeroRecord,AeroKey> repository = new AerospikeRepository<>(aeroClient);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.expiration=300;
        writePolicy.sendKey=true;

        AeroRecord record = new AeroRecord();
        AeroKey key = new AeroKey();
        String mykey = UUID.randomUUID().toString();
        key.setKey(mykey);
        key.setNamespace("test");
        key.setSet("Account");
        AeroBin<String> bin = new AeroBin<String>();
        bin.setName("data1");
        bin.setValue(UUID.randomUUID().toString());
        System.out.println(">bin1 " + bin.getValue());
        AeroBin<String> bin2 = new AeroBin<String>();
        bin2.setName("data2");
        bin2.setValue(UUID.randomUUID().toString());
        System.out.println(">bin2 " + bin2.getValue());
        record.setKey(key);
        record.addBin(bin);
        record.addBin(bin2);
        repository.save(writePolicy,record);
        System.out.println("Record saved");
        AeroRecord readRecord=repository.get(key,AeroRecord.class);
        System.out.println("Read data > " + readRecord.getBins().get(0).getValue());
    }
}
