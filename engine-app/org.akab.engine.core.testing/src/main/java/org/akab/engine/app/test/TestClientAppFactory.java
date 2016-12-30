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

    public static ClientApp make(ServerEntryPointContext entryPointContext){

        TestClientRouter clientRouter = new TestClientRouter();
        TestServerRouter serverRouter = new TestServerRouter(entryPointContext);
        RequestEventProcessor requestEventProcessor = new RequestEventProcessor();
        TestEventBus eventBus=new TestEventBus(requestEventProcessor);

        ClientApp clientApp = new ClientApp.ClientAppBuilder()
                .clientRouter(clientRouter)
                .serverRouter(serverRouter)
                .eventsBus(eventBus)
                .presentersRepository(new TestInMemoryPresenterRepository())
                .requestRepository(new InMemoryRequestsRepository())
                .viewsRepository(new TestInMemoryViewRepository())
                .contributionsRepository(new InMemoryContributionRepository())
                .pathToRequestMapperRepository(new InMemoryPathToRequestMappersRepository())
                .tokenConstruct(new PathTokenConstructor())
                .urlHistory(new TestUrlHistory())
                .mainExtensionPoint(new MainExtensionPoint() {
                    @Override
                    public MainContext context() {
                        return new MainContext() {
                            @Override
                            public void appendElementToRoot(Element e) {

                            }

                            @Override
                            public void appendWidgetToRoot(IsWidget w) {

                            }
                        };
                    }
                })
                .build();

        return clientApp;
    }

}
