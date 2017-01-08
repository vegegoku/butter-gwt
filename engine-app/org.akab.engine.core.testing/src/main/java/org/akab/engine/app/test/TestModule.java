package org.akab.engine.app.test;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;
import org.akab.engine.core.server.ServerConfigurationLoader;

public class TestModule {

    public ClientApp init(ServerEntryPointContext entryPointContext) {
        new ServerConfigurationLoader().loadModules();
        return TestClientAppFactory.make(entryPointContext);
    }

    public void replacePresenter(String presenterName, TestPresenterFactory presenterFactory) {
        ((TestInMemoryPresenterRepository) ClientApp.make().getPresentersRepository())
                .replacePresenter(presenterName, presenterFactory);
    }

    public void replaceView(String presenterName, TestViewFactory viewFactory) {
        ((TestInMemoryViewRepository) ClientApp.make().getViewsRepository()).replaceView(presenterName, viewFactory);
    }

    public void configureModule(ModuleConfiguration configuration) {
        new ModuleConfigurator().configureModule(configuration);
    }

    public void run() {
        ClientApp.make().run();
    }

    public <C extends Contribution> C getContribution(Class<C> contributionClass){
        return TestClientAppFactory.contributionsRepository.getContribution(contributionClass);
    }
}
