package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.*;

import java.util.Collection;

class DefaultRequestExecutor implements RequestExecutor {

    private final HandlersRepository handlersRepository;
    private final InterceptorsRepository interceptorsRepository;

    public DefaultRequestExecutor(HandlersRepository handlersRepository, InterceptorsRepository interceptorsRepository) {
        this.handlersRepository = handlersRepository;
        this.interceptorsRepository = interceptorsRepository;
    }

    @Override
    public ServerResponse executeRequest(ServerRequest request, ServerEntryPointContext context) {
        getInterceptors(request, context).forEach(i -> i.intercept(request, context));
        getGlobalInterceptors(context).forEach(i -> i.intercept(request, context));
        return handlersRepository.findHandler(request.getClass().getCanonicalName()).handleRequest(request);
    }

    private Collection<RequestInterceptor> getInterceptors(ServerRequest request, ServerEntryPointContext context) {
        return interceptorsRepository.findInterceptors(request.getClass().getCanonicalName(),context.getClass().getCanonicalName());
    }

    private Collection<GlobalInterceptor> getGlobalInterceptors(ServerEntryPointContext context) {
        return interceptorsRepository.findGlobalInterceptors(context.getClass().getCanonicalName());
    }
}
