package org.moita.persistence;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

public class ElasticSearch extends AbstractElasticSearch {

    public ElasticSearch(String index) {
        super(index);
    }

    public IndexResponse create(String json) throws IOException {

        IndexRequest request = createIndexRequest(null, json);

        return client.index(request, DEFAULT);
    }

    public IndexResponse create(String id, String json) throws IOException {

        IndexRequest request = createIndexRequest(id, json);

        return client.index(request, DEFAULT);
    }

    public void createAsync(String id, String json, ActionListener listener) {

        IndexRequest request = createIndexRequest(id, json);

        client.indexAsync(request, DEFAULT, listener);
    }

    public UpdateResponse update(String id, String json) throws IOException {

        UpdateRequest request = createUpdateRequest(id, json);

        return client.update(request, DEFAULT);
    }

    public void updateAsync(String id, String json, ActionListener listener) {

        UpdateRequest request = createUpdateRequest(id, json);

        client.updateAsync(request, DEFAULT, listener);
    }

    public DeleteResponse delete(String id) throws IOException {

        DeleteRequest request = createDeleteRequest(id);

        return client.delete(request, DEFAULT);

    }

    public void deleteAsync(String id, ActionListener listener) {

        DeleteRequest request = createDeleteRequest(id);

        client.deleteAsync(request, DEFAULT, listener);
    }

    public GetResponse get(String id) throws IOException {

        GetRequest request = createGetRequest(id);

        return client.get(request, DEFAULT);
    }

    public void getAsync(String id, ActionListener listener) {

        GetRequest request = createGetRequest(id);

        client.getAsync(request, DEFAULT, listener);
    }

    public boolean isExists(String id) throws IOException {

        GetRequest request = createGetRequest(id);

        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        return client.exists(request, DEFAULT);

    }
}
