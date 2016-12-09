package org.akab.rafa;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.HandlersRepository;
import org.akab.engine.core.api.shared.server.InterceptorsRepository;
import org.akab.engine.core.api.shared.server.ServerApp;
import org.akab.engine.core.api.shared.service.ServerService;
import org.akab.engine.core.server.InMemoryHandlersRepository;
import org.akab.engine.core.server.InMemoryInterceptorsRepository;
import org.akab.engine.core.server.RpcEntryPointContext;
import org.akab.rafa.hello.world.server.HelloWorldHandler;
import org.akab.engine.core.server.DefaultRequestExecutor;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;

@WebServlet(value="/app/serverService" , name = "ServerRPC")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

    private static final long serialVersionUID = 9016180344293259071L;

    private static final Logger LOGGER=Logger.getLogger(ServerServiceImpl.class.getName());

    @Override
    public ServerResponse executeRequest(ServerRequest request) throws Exception {
//        HandlersRepository handlersRepository=new InMemoryHandlersRepository();
//        InterceptorsRepository interceptorsRepository=new InMemoryInterceptorsRepository();
//        new ServerApp.ServerAppBuilder().handlersRepository(handlersRepository).interceptorsRepository(interceptorsRepository).executor(new DefaultRequestExecutor(handlersRepository, interceptorsRepository)).build();
//        ServerApp sa=ServerApp.make();
//        sa.registerHandler(HelloWordsArgs.class.getCanonicalName(), new HelloWorldHandler());
        return ServerApp.make().executeRequest(request, new RpcEntryPointContext(getThreadLocalRequest(), getThreadLocalResponse()));
    }
}
