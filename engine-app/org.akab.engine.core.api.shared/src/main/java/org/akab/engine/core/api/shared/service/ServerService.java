package org.akab.engine.core.api.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;

@RemoteServiceRelativePath("serverService")
public interface ServerService extends RemoteService{
    Response executeRequest(ServerArgs arguments) throws Exception;
}
