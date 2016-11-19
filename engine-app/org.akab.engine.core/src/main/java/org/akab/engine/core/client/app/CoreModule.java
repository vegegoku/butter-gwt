package org.akab.engine.core.client.app;

import com.google.gwt.user.client.History;
import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.History.PathTokenConstructor;
import org.akab.engine.core.client.events.ClientEventFactory;
import org.akab.engine.core.client.events.RequestEventProcessor;
import org.akab.engine.core.client.events.ServerEventFactory;
import org.akab.engine.core.client.events.SimpleEventsBus;
import org.akab.engine.core.client.extensions.InMemoryContributionRepository;
import org.akab.engine.core.client.history.HistoryChangeHandler;
import org.akab.engine.core.client.history.InMemoryPathToRequestMappersRepository;
import org.akab.engine.core.client.mvp.presenter.InMemoryPresentersRepository;
import org.akab.engine.core.client.mvp.view.InMemoryViewRepository;
import org.akab.engine.core.client.request.ClientRouter;
import org.akab.engine.core.client.request.InMemoryRequestsRepository;
import org.akab.engine.core.client.request.ServerRouter;

public class CoreModule {

    public static void init(){
        ClientRouter clientRouter = new ClientRouter(new ClientEventFactory());
        ServerRouter serverRouter=new ServerRouter(new ServerEventFactory());

        RequestEventProcessor requestEventProcessor = new RequestEventProcessor();
        SimpleEventsBus eventBus = new SimpleEventsBus(requestEventProcessor);

        ClientApp clientApp=new ClientApp.ClientAppBuilder().clientRouter(clientRouter).serverRouter(serverRouter).eventsBus(eventBus).presentersRepository(new InMemoryPresentersRepository()).requestRepository(new InMemoryRequestsRepository()).viewsRepository(new InMemoryViewRepository()).contributionsRepository(new InMemoryContributionRepository()).pathToRequestMapperRepository(new InMemoryPathToRequestMappersRepository()).tokenConstruct(new PathTokenConstructor()).build();

        History.addValueChangeHandler(new HistoryChangeHandler(clientApp.getPathToRequestMappersRepository()));
    }
}
