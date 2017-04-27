package org.akab.engine.core.test;


import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.history.PathTokenConstructor;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;
import org.akab.engine.core.client.events.RequestEventProcessor;
import org.akab.engine.core.client.history.InMemoryPathToRequestMappersRepository;
import org.akab.engine.core.client.request.InMemoryRequestsRepository;

public class TestClientAppFactory {

    static TestInMemoryPresenterRepository presentersRepository;
    static InMemoryRequestsRepository requestRepository;
    static TestInMemoryViewRepository viewsRepository;
    static TestInMemoryContributionsRepository contributionsRepository;
    static InMemoryPathToRequestMappersRepository pathToRequestMappersRepository;

    private TestClientAppFactory() {
    }

    public static ClientApp make(ServerEntryPointContext entryPointContext, TestFilterChain filterChain) {

        TestClientRouter clientRouter = new TestClientRouter();
        TestServerRouter serverRouter = new TestServerRouter(entryPointContext, filterChain);
        RequestEventProcessor requestEventProcessor = new RequestEventProcessor();
        TestEventBus eventBus = new TestEventBus(requestEventProcessor);

        presentersRepository = new TestInMemoryPresenterRepository();
        requestRepository = new InMemoryRequestsRepository();
        viewsRepository = new TestInMemoryViewRepository();
        contributionsRepository = new TestInMemoryContributionsRepository();
        pathToRequestMappersRepository = new InMemoryPathToRequestMappersRepository();
        return new ClientApp.ClientAppBuilder()
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
                        //default empty implementation for fake main extension point.
                    }

                    @Override
                    public void appendWidgetToRoot(IsWidget w) {
                        //default empty implementation for fake main extension point.
                    }

                    @Override
                    public void removeElement(Element e) {

                    }

                    @Override
                    public void removeWidget(IsWidget w) {

                    }
                })
                .build();
    }

}
