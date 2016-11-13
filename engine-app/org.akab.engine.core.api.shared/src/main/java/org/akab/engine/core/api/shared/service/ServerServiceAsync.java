package org.akab.engine.core.api.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;

public interface ServerServiceAsync {
    void executeRequest(ServerArgs arguments, AsyncCallback<Response> callback)
            throws IllegalArgumentException;
}
