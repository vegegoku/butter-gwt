package org.akab.engine.core.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;

@FunctionalInterface
public interface ServerServiceAsync {
    void executeRequest(ServerRequest request, AsyncCallback<ServerResponse> callback);
}
