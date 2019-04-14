package org.moita.listener;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;

public class GetResponseListener implements ActionListener<GetResponse> {

    public static final GetResponseListener aListener() {
        return new GetResponseListener();
    }

    @Override
    public void onResponse(GetResponse indexResponse) {
        System.out.println(indexResponse.toString());
    }

    @Override
    public void onFailure(Exception e) {
        System.err.println(e.getMessage());
    }
}
