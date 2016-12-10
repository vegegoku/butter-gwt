package org.akab.engine.app;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.app.FirstAnnotatedClassWithPresenter;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.app.SecondPresenterInterface;
import org.akab.engine.app.FirstPresenterInterface;
import org.akab.engine.app.SecondAnnotatedClassWithPresenter;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

public class PresentersRegistrationsModuleConfiguration implements ModuleConfiguration {
    @Override
    public void registerPresenters(PresenterRegistry registry) {
        registry.registerPresenter(new LazyPresenterLoader(SecondPresenterInterface.class.getCanonicalName(), SecondAnnotatedClassWithPresenter.class.getCanonicalName()) {
            @Override
            protected Presentable make() {
                return new SecondAnnotatedClassWithPresenter();
            }
        });
        registry.registerPresenter(new LazyPresenterLoader(FirstPresenterInterface.class.getCanonicalName(), FirstAnnotatedClassWithPresenter.class.getCanonicalName()) {
            @Override
            protected Presentable make() {
                return new FirstAnnotatedClassWithPresenter();
            }
        });
    }
}