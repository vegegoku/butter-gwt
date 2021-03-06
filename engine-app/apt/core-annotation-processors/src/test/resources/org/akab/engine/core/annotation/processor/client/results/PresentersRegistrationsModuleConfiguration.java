package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.annotation.processor.client.FirstAnnotatedClassWithPresenter;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.annotation.processor.client.FirstPresenterInterface;
import org.akab.engine.core.annotation.processor.client.SecondAnnotatedClassWithPresenter;
import org.akab.engine.core.annotation.processor.client.SecondPresenterInterface;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

public class PresentersRegistrationsModuleConfiguration implements ModuleConfiguration {
    @Override
    public void registerPresenters(PresenterRegistry registry) {
        registry.registerPresenter(new LazyPresenterLoader(FirstPresenterInterface.class.getCanonicalName(), FirstAnnotatedClassWithPresenter.class.getCanonicalName()) {
            @Override
            protected Presentable make() {
                return new FirstAnnotatedClassWithPresenter();
            }
        });
        registry.registerPresenter(new LazyPresenterLoader(SecondPresenterInterface.class.getCanonicalName(), SecondAnnotatedClassWithPresenter.class.getCanonicalName()) {
            @Override
            protected Presentable make() {
                return new SecondAnnotatedClassWithPresenter();
            }
        });
    }
}