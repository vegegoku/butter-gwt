package org.akab.rafa;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.ServerApp;
import org.akab.engine.core.api.shared.service.ServerService;
import org.akab.engine.core.server.RpcEntryPointContext;

import java.util.logging.Logger;

public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

    private static final long serialVersionUID = 9016180344293259071L;

    private static final Logger LOGGER=Logger.getLogger(ServerServiceImpl.class.getName());

    @Override
    public ServerResponse executeRequest(ServerRequest request) throws Exception {
        return ServerApp.make().executeRequest(request, new RpcEntryPointContext(getThreadLocalRequest(), getThreadLocalResponse()));
    }
}
