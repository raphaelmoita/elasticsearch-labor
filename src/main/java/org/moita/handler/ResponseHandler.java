package org.moita.handler;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateResponse;

public class ResponseHandler {

    public void checkResponse(IndexResponse response) {
        String index = response.getIndex();
        String id = response.getId();

        if (response.getResult() == DocWriteResponse.Result.CREATED) {
            System.out.println("index: " + index + " id: " + id + " created!");
        } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
            System.out.println("index: " + index + " id: " + id + " updated!");
        }

        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            // TODO: ???
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                // TODO: ???
            }
        }
    }

    public void checkResponse(UpdateResponse response) {
        String index = response.getIndex();
        String id = response.getId();
        long version = response.getVersion();

        if (response.getResult() == DocWriteResponse.Result.CREATED) {
            System.out.println("index: " + index + " id: " + id + " version: " + version + " created!");
        } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
            System.out.println("index: " + index + " id: " + id + " version: " + version + " updated!");
        } else if (response.getResult() == DocWriteResponse.Result.DELETED) {
            System.out.println("index: " + index + " id: " + id + " version: " + version + " deleted!");
        } else if (response.getResult() == DocWriteResponse.Result.NOOP) {
            System.out.println("index: " + index + " id: " + id + " version: " + version + " noop!");
        }
    }
}
