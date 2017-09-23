package org.deeaae.learnings.aerospike.client;

import com.aerospike.client.AerospikeClient;

public class AeroClient {
    AerospikeClient client;

    public AerospikeClient createClient() {
        //TODO Use snakeyml for configuration
        //TODO Move hostname and port to configuration.
        client = new AerospikeClient("127.0.0.1",3000);
        return client;
    }

    public AerospikeClient getClient() {
        return client;
    }
}
