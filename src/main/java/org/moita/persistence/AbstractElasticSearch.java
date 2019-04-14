package org.moita.persistence;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import static java.util.Objects.nonNull;
import static org.elasticsearch.common.xcontent.XContentType.JSON;

public class AbstractElasticSearch {

    protected static final String DEFAULT_HOST = "localhost";
    protected static final int DEFAULT_PORT = 9200;
    protected static final String DEFAULT_SCHEME = "http";

    protected String getHost() {
        return DEFAULT_HOST;
    }

    protected int getPort() {
        return DEFAULT_PORT;
    }

    protected String getSchema() {
        return DEFAULT_SCHEME;
    }

    protected final RestHighLevelClient client;

    private final String index;

    public AbstractElasticSearch(String index) {
        this.index = index;
        this.client = new RestHighLevelClient(RestClient.builder(new HttpHost(getHost(), getPort(), getSchema())));
    }

    protected IndexRequest createIndexRequest(String id, String json) {
        IndexRequest request = new IndexRequest(index);

        if (nonNull(id)) {
            request.id(id);
        }

        request.source(json, JSON);

        return request;
    }

    protected UpdateRequest createUpdateRequest(String id, String json) {
        UpdateRequest request = new UpdateRequest(index, id);
        request.doc(json, JSON);

        return request;
    }

    protected DeleteRequest createDeleteRequest(String id) {
        DeleteRequest request = new DeleteRequest(index, id);

        return request;
    }

    protected GetRequest createGetRequest(String id) {
        GetRequest request = new GetRequest(index, id);

        return request;
    }
}
