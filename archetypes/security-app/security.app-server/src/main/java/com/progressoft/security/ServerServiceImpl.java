package com.progressoft.security;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.server.ServerApp;
import org.akab.engine.core.api.shared.service.ServerService;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import org.akab.engine.core.server.RpcEntryPointContext;

public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

    private static final long serialVersionUID = 9016180344293259071L;

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(ServerServiceImpl.class.getName());

    @Override
    public ServerResponse executeRequest(ServerRequest request) {
        LOGGER.info("Server call recieved for request : "+request.toString());
        try {
            return ServerApp
                    .make().executeRequest(request,
                            new RpcEntryPointContext(getThreadLocalRequest(), getThreadLocalResponse()));
        }catch (Exception e){
            LOGGER.error("Request failed on server", e);
            throw e;
        }
    }
}
