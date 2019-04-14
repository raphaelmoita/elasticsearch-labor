package org.moita.listener;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateResponse;

public class DeleteResponseListener implements ActionListener<DeleteResponse> {

    public static final DeleteResponseListener aListener() {
        return new DeleteResponseListener();
    }

    @Override
    public void onResponse(DeleteResponse indexResponse) {
        System.out.println(indexResponse.toString());
    }

    @Override
    public void onFailure(Exception e) {
        System.err.println(e.getMessage());
    }
}
