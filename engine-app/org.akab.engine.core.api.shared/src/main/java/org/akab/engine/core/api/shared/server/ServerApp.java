package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;

import java.util.Objects;

public class ServerApp implements HandlerRegistry, InterceptorsRegistry{

    private static RequestExecutor requestExecutor;
    private static HandlersRepository handlersRepository;
    private static InterceptorsRepository interceptorsRepository;

    private ServerApp() {
    }

    private ServerApp(RequestExecutor requestExecutor , HandlersRepository handlersRepository, InterceptorsRepository interceptorsRepository) {
        this.requestExecutor=requestExecutor;
        this.handlersRepository=handlersRepository;
        this.interceptorsRepository=interceptorsRepository;
    }

    public static ServerApp make(){
        return new ServerApp();
    }

    public ServerApp run() {
        return this;
    }

    public ServerResponse executeRequest(ServerRequest request, ServerEntryPointContext context) {
        return requestExecutor.executeRequest(request, context);
    }

    public void registerHandler(String request, RequestHandler handler) {
        handlersRepository.registerHandler(request, handler);
    }

    public HandlersRepository handlersRepository() {
        return handlersRepository;
    }

    @Override
    public void registerInterceptor(String requestName, String entryPointName, RequestInterceptor interceptor) {
        interceptorsRepository.addInterceptor(requestName, entryPointName, interceptor);
    }

    @Override
    public void registerGlobalInterceptor(String entryPointName, GlobalInterceptor interceptor) {
        interceptorsRepository.addGlobalInterceptor(entryPointName, interceptor);
    }

    public static class ServerAppBuilder{

        private RequestExecutor requestExecutor;
        private HandlersRepository handlersRepository;
        private InterceptorsRepository interceptorsRepository;

        public ServerAppBuilder() {
        }

        public ServerAppBuilder executor(RequestExecutor requestExecutor){
            this.requestExecutor=requestExecutor;
            return this;
        }

        public ServerAppBuilder handlersRepository(HandlersRepository handlersRepository){
            this.handlersRepository=handlersRepository;
            return this;
        }

        public ServerAppBuilder interceptorsRepository(InterceptorsRepository interceptorsRepository){
            this.interceptorsRepository=interceptorsRepository;
            return this;
        }

        public ServerApp build(){
            if(Objects.isNull(requestExecutor))
                throw new RequestExecutorIsRequired();
            if(Objects.isNull(handlersRepository))
                throw new HandlersRepositoryIsRequired();
            if(Objects.isNull(interceptorsRepository))
                throw new InterceptorsRepositoryIsRequired();

            return new ServerApp(requestExecutor, handlersRepository, interceptorsRepository);
        }

        private class RequestExecutorIsRequired extends RuntimeException {
        }

        private class HandlersRepositoryIsRequired extends RuntimeException {
        }

        private class InterceptorsRepositoryIsRequired extends RuntimeException {
        }
    }
}
