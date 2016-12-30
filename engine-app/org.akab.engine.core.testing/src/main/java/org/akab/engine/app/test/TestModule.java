package org.akab.engine.app.test;


import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.client.mvp.view.View;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;
import org.akab.engine.core.server.ServerConfigurationLoader;

public class TestModule {

    public ClientApp init(ServerEntryPointContext entryPointContext){
        new ServerConfigurationLoader().loadModules();
        return TestClientAppFactory.make(entryPointContext);
    }

    public void replacePresenter(String presenterName, Presentable presenter){
        ((TestInMemoryPresenterRepository)ClientApp.make().getPresentersRepository()).replacePresenter(presenterName, presenter);
    }
    public void replaceView(String presenterName, View view){
        ((TestInMemoryViewRepository)ClientApp.make().getViewsRepository()).replaceView(presenterName, view);
    }

    public void configureModule(ModuleConfiguration configuration){
        new ModuleConfigurator().configureModule(configuration);
    }
}
