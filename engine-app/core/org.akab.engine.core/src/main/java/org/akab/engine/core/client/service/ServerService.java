package org.akab.engine.core.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;
@FunctionalInterface
@RemoteServiceRelativePath("serverService")
public interface ServerService extends RemoteService{
    ServerResponse executeRequest(ServerRequest request);
}
