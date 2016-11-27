package org.akab.engine.core.api.client.mvp;

import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.View;

public interface ViewRegistry{
    void registerView(LazyViewLoader lazyViewLoader);
}