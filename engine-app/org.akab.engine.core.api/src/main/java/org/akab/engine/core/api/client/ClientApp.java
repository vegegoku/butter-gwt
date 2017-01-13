package org.akab.engine.core.api.client;

import org.akab.engine.core.api.client.history.*;
import org.akab.engine.core.api.client.events.EventsBus;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.api.client.extension.ContributionsRepository;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.api.client.request.*;
import org.akab.engine.core.api.client.mvp.ViewRegistry;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.api.client.mvp.presenter.PresentersRepository;
import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.ViewsRepository;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import java.util.LinkedList;
import java.util.List;

public class ClientApp
        implements PresenterRegistry, RequestRegistry, ViewRegistry, InitialTaskRegistry, ContributionsRegistry,
        PathToRequestMapperRegistry {

    private static RequestRouter<ClientRequest> clientRouter;
    private static RequestRouter<ClientServerRequest> serverRouter;
    private static EventsBus eventsBus;
    private static RequestsRepository requestRepository;
    private static PresentersRepository presentersRepository;
    private static ViewsRepository viewsRepository;
    private static ContributionsRepository contributionsRepository;
    private static PathToRequestMappersRepository pathToRequestMappersRepository;
    private static TokenConstruct tokenConstruct;
    private static MainExtensionPoint mainExtensionPoint;
    private static UrlHistory urlHistory;
    private static List<InitializeTask> initialTasks;

    private ClientApp() {
    }

    private ClientApp(RequestRouter<ClientRequest> clientRouter, RequestRouter<ClientServerRequest> serverRouter,
                      EventsBus eventsBus, RequestsRepository requestRepository,
                      PresentersRepository presentersRepository, ViewsRepository viewsRepository,
                      ContributionsRepository contributionsRepository,
                      PathToRequestMappersRepository pathToRequestMappersRepository, TokenConstruct tokenConstruct,
                      UrlHistory urlHistory, MainExtensionPoint mainExtensionPoint) {
        ClientApp.clientRouter = clientRouter;
        ClientApp.serverRouter = serverRouter;
        ClientApp.eventsBus = eventsBus;
        ClientApp.requestRepository = requestRepository;
        ClientApp.presentersRepository = presentersRepository;
        ClientApp.viewsRepository = viewsRepository;
        ClientApp.contributionsRepository = contributionsRepository;
        ClientApp.pathToRequestMappersRepository = pathToRequestMappersRepository;
        ClientApp.tokenConstruct = tokenConstruct;
        ClientApp.mainExtensionPoint = mainExtensionPoint;
        ClientApp.urlHistory = urlHistory;
        ClientApp.initialTasks=new LinkedList<>();

    }

    @Override
    public void registerPresenter(LazyPresenterLoader lazyPresenterLoader) {
        presentersRepository.registerPresenter(lazyPresenterLoader);
    }

    @Override
    public void registerRequest(String requestName, String presenterName) {
        requestRepository.registerRequest(new RequestHolder(requestName, presenterName));
    }

    @Override
    public void registerView(LazyViewLoader lazyViewLoader) {
        viewsRepository.registerView(lazyViewLoader);
    }

    @Override
    public void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution) {
        contributionsRepository.registerContribution(extensionPoint, contribution);
    }

    @Override
    public void registerMapper(String path, RequestFromPath mapper) {
        pathToRequestMappersRepository.registerMapper(path, mapper);
    }

    @Override
    public void registerInitialTask(InitializeTask task) {
        initialTasks.add(task);
    }

    public static ClientApp make() {
        return new ClientApp();
    }

    public RequestRouter<ClientRequest> getClientRouter() {
        return clientRouter;
    }

    public RequestRouter<ClientServerRequest> getServerRouter() {
        return serverRouter;
    }

    public EventsBus getEventsBus() {
        return eventsBus;
    }

    public RequestsRepository getRequestRepository() {
        return requestRepository;
    }

    public PresentersRepository getPresentersRepository() {
        return presentersRepository;
    }

    public ViewsRepository getViewsRepository() {
        return viewsRepository;
    }

    public PathToRequestMappersRepository getPathToRequestMappersRepository() {
        return pathToRequestMappersRepository;
    }

    public TokenConstruct getTokenConstruct() {
        return tokenConstruct;
    }

    public void configureModule(ModuleConfiguration configuration) {
        configuration.registerPresenters(this);
        configuration.registerRequests(this);
        configuration.registerViews(this);
        configuration.registerContributions(this);
        configuration.registerInitialTasks(this);
        configuration.registerPathMappers(this);
    }

    public void run() {
        initialTasks.forEach(InitializeTask::execute);
        Contributions.apply(MainExtensionPoint.class, mainExtensionPoint);
    }


    public void applyContributions(Class<? extends ExtensionPoint> extensionPointInterface,
                                   ExtensionPoint extensionPoint) {
        contributionsRepository.findExtensionPointContributions(extensionPointInterface)
                .forEach(c -> c.contribute(extensionPoint));
    }

    public void applyUrlHistory(){
        urlHistory.apply(getTokenConstruct().toUrl());
    }

    public static class ClientAppBuilder {

        private RequestRouter<ClientRequest> clientRouter;
        private RequestRouter<ClientServerRequest> serverRouter;
        private EventsBus eventsBus;
        private RequestsRepository requestRepository;
        private PresentersRepository presentersRepository;
        private ViewsRepository viewsRepository;
        private ContributionsRepository contributionsRepository;
        private PathToRequestMappersRepository pathToRequestMappersRepository;
        private TokenConstruct tokenConstruct;
        private MainExtensionPoint mainExtensionPoint;
        private UrlHistory urlHistory;

        public ClientAppBuilder() {
            //this is  builder for the client app.
        }

        public ClientAppBuilder clientRouter(RequestRouter<ClientRequest> clientRouter) {
            this.clientRouter = clientRouter;
            return this;
        }

        public ClientAppBuilder serverRouter(RequestRouter<ClientServerRequest> serverRouter) {
            this.serverRouter = serverRouter;
            return this;
        }

        public ClientAppBuilder eventsBus(EventsBus eventsBus) {
            this.eventsBus = eventsBus;
            return this;
        }

        public ClientAppBuilder requestRepository(RequestsRepository requestRepository) {
            this.requestRepository = requestRepository;
            return this;
        }

        public ClientAppBuilder presentersRepository(PresentersRepository presentersRepository) {
            this.presentersRepository = presentersRepository;
            return this;
        }

        public ClientAppBuilder viewsRepository(ViewsRepository viewsRepository) {
            this.viewsRepository = viewsRepository;
            return this;
        }

        public ClientAppBuilder contributionsRepository(ContributionsRepository contributionsRepository) {
            this.contributionsRepository = contributionsRepository;
            return this;
        }

        public ClientAppBuilder pathToRequestMapperRepository(
                PathToRequestMappersRepository pathToRequestMappersRepository) {
            this.pathToRequestMappersRepository = pathToRequestMappersRepository;
            return this;
        }

        public ClientAppBuilder tokenConstruct(TokenConstruct tokenConstruct) {
            this.tokenConstruct = tokenConstruct;
            return this;
        }

        public ClientAppBuilder urlHistory(UrlHistory urlHistory) {
            this.urlHistory = urlHistory;
            return this;
        }

        public ClientAppBuilder mainExtensionPoint(MainExtensionPoint mainExtensionPoint) {
            this.mainExtensionPoint = mainExtensionPoint;
            return this;
        }


        public ClientApp build() {
            return new ClientApp(clientRouter, serverRouter, eventsBus, requestRepository, presentersRepository,
                    viewsRepository, contributionsRepository, pathToRequestMappersRepository, tokenConstruct,
                    urlHistory, mainExtensionPoint);
        }
    }
}
