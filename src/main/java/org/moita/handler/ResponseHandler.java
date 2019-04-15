package org.moita.handler;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;

public class ResponseHandler {

    public void checkResponse(DocWriteResponse response)
    {
        String index = response.getIndex();
        String id = response.getId();
        long version = response.getVersion();

        switch (response.getResult()) {
            case UPDATED:
                System.out.println("index: " + index + " id: " + id + " version: " + version + " updated!");
                break;
            case CREATED:
                System.out.println("index: " + index + " id: " + id + " version: " + version + " created!");
                break;
            case NOOP:
                System.out.println("index: " + index + " id: " + id + " version: " + version + " noop!");
                break;
            case NOT_FOUND:
                System.out.println("index: " + index + " id: " + id + " version: " + version + " not found!");
                break;
            case DELETED:
                System.out.println("index: " + index + " id: " + id + " version: " + version + " deleted!");
                break;
            default:
                System.out.println("index: " + index + " id: " + id + " version: " + version + " unknown operation [" + response.getResult() + "]");
        }

        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            System.err.println("ShardInfo total: " + shardInfo.getTotal());
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                System.err.println("Reason: " + failure.reason());
            }
        }
    }
}
