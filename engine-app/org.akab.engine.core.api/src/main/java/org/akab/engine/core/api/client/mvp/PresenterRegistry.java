package org.akab.engine.core.api.client.mvp;

import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;

public interface PresenterRegistry{
    void registerPresenter(LazyPresenterLoader lazyPresenterLoader);
}