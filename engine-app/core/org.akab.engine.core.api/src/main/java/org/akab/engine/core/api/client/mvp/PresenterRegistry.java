package org.akab.engine.core.api.client.mvp;

import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;

@FunctionalInterface
public interface PresenterRegistry{
    void registerPresenter(LazyPresenterLoader lazyPresenterLoader);
}