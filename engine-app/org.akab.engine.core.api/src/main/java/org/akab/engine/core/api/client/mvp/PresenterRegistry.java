package org.akab.engine.core.api.client.mvp;

import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;

public interface PresenterRegistry{
    void registerPresenter(String name, ClientPresenter presenter);
}