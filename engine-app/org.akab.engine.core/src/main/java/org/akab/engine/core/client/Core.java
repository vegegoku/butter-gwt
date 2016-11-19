package org.akab.engine.core.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.client.app.CoreModule;
import org.akab.engine.core.client.events.ClientEventFactory;
import org.akab.engine.core.client.events.RequestEventProcessor;
import org.akab.engine.core.client.events.ServerEventFactory;
import org.akab.engine.core.client.events.SimpleEventsBus;
import org.akab.engine.core.client.extensions.InMemoryContributionRepository;
import org.akab.engine.core.api.client.History.PathTokenConstructor;
import org.akab.engine.core.client.mvp.presenter.InMemoryPresentersRepository;
import org.akab.engine.core.client.mvp.view.InMemoryViewRepository;
import org.akab.engine.core.client.request.ClientRouter;
import org.akab.engine.core.client.request.InMemoryRequestsRepository;
import org.akab.engine.core.client.request.ServerRouter;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public class Core implements EntryPoint {

    private final static CoreLogger LOGGER= CoreLoggerFactory.getLogger(Core.class);

    public void onModuleLoad() {

        CoreModule.init();

        LOGGER.info("Init engine Core...");
    }
}
