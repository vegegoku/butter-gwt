package org.akab.engine.core.test;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;

import javax.servlet.FilterChain;

public class TestModule {

    public ClientApp init(ServerEntryPointContext entryPointContext, TestFilterChain filterChain) {
        return TestClientAppFactory.make(entryPointContext, filterChain);
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

    public <C extends Contribution> C getContribution(Class<C> contributionClass) {
        return TestClientAppFactory.contributionsRepository.getContribution(contributionClass);
    }

}
