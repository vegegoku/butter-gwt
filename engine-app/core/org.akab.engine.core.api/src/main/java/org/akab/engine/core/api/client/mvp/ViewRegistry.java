package org.akab.engine.core.api.client.mvp;

import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;

@FunctionalInterface
public interface ViewRegistry{
    void registerView(LazyViewLoader lazyViewLoader);
}