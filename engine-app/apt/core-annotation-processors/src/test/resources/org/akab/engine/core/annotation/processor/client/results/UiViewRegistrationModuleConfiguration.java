package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.mvp.ViewRegistry;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithUiView;
import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.annotation.processor.client.PresenterInterface;
import org.akab.engine.core.api.client.mvp.view.View;

public class UiViewRegistrationModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerViews(ViewRegistry registry) {
        registry.registerView(new LazyViewLoader(PresenterInterface.class.getCanonicalName()) {
            @Override
            protected View make() {
                return new AnnotatedClassWithUiView();
            }
        });
    }
}