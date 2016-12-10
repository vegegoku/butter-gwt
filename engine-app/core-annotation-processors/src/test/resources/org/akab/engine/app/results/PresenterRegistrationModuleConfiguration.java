package org.akab.engine.app;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.app.DefaultAnnotatedClassWithPresenter;
import org.akab.engine.app.PresenterInterface;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

public class PresenterRegistrationModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerPresenters(PresenterRegistry registry) {
        registry.registerPresenter(new LazyPresenterLoader(PresenterInterface.class.getCanonicalName(), DefaultAnnotatedClassWithPresenter.class.getCanonicalName()) {
            @Override
            protected Presentable make() {
                return new DefaultAnnotatedClassWithPresenter();
            }
        });
    }
}