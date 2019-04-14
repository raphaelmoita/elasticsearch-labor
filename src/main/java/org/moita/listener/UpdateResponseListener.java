package org.moita.listener;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.update.UpdateResponse;

public class UpdateResponseListener implements ActionListener<UpdateResponse> {

    public static final UpdateResponseListener aListener() {
        return new UpdateResponseListener();
    }

    @Override
    public void onResponse(UpdateResponse indexResponse) {
        System.out.println(indexResponse.toString());
    }

    @Override
    public void onFailure(Exception e) {
        System.err.println(e.getMessage());
    }
}
