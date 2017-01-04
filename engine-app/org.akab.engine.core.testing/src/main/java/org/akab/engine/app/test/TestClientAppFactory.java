package org.akab.engine.app.test;


import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.History.PathTokenConstructor;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.core.api.shared.server.HandlersRepository;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;
import org.akab.engine.core.client.events.RequestEventProcessor;
import org.akab.engine.core.client.extensions.InMemoryContributionRepository;
import org.akab.engine.core.client.history.InMemoryPathToRequestMappersRepository;
import org.akab.engine.core.client.mvp.presenter.InMemoryPresentersRepository;
import org.akab.engine.core.client.mvp.view.InMemoryViewRepository;
import org.akab.engine.core.client.request.InMemoryRequestsRepository;

public class TestClientAppFactory {

    static TestInMemoryPresenterRepository presentersRepository;
    static InMemoryRequestsRepository requestRepository;
    static TestInMemoryViewRepository viewsRepository;
    static InMemoryContributionRepository contributionsRepository;
    static InMemoryPathToRequestMappersRepository pathToRequestMappersRepository;

    public static ClientApp make(ServerEntryPointContext entryPointContext) {

        TestClientRouter clientRouter = new TestClientRouter();
        TestServerRouter serverRouter = new TestServerRouter(entryPointContext);
        RequestEventProcessor requestEventProcessor = new RequestEventProcessor();
        TestEventBus eventBus = new TestEventBus(requestEventProcessor);

        presentersRepository = new TestInMemoryPresenterRepository();
        requestRepository = new InMemoryRequestsRepository();
        viewsRepository = new TestInMemoryViewRepository();
        contributionsRepository = new InMemoryContributionRepository();
        pathToRequestMappersRepository = new InMemoryPathToRequestMappersRepository();
        ClientApp clientApp = new ClientApp.ClientAppBuilder()
                .clientRouter(clientRouter)
                .serverRouter(serverRouter)
                .eventsBus(eventBus)
                .presentersRepository(presentersRepository)
                .requestRepository(requestRepository)
                .viewsRepository(viewsRepository)
                .contributionsRepository(contributionsRepository)
                .pathToRequestMapperRepository(pathToRequestMappersRepository)
                .tokenConstruct(new PathTokenConstructor())
                .urlHistory(new TestUrlHistory())
                .mainExtensionPoint(() -> new MainContext() {
                    @Override
                    public void appendElementToRoot(Element e) {

                    }

                    @Override
                    public void appendWidgetToRoot(IsWidget w) {

                    }
                })
                .build();

        return clientApp;
    }

}
