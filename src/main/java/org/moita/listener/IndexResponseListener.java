package org.moita.listener;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexResponse;

public class IndexResponseListener implements ActionListener<IndexResponse> {

    public static final IndexResponseListener aListener() {
        return new IndexResponseListener();
    }

    @Override
    public void onResponse(IndexResponse indexResponse) {
        System.out.println(indexResponse.toString());
    }

    @Override
    public void onFailure(Exception e) {
        System.err.println(e.getMessage());
    }
}
