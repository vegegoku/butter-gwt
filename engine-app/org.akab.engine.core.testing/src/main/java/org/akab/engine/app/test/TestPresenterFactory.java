package org.akab.engine.app.test;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;

@FunctionalInterface
public interface TestPresenterFactory {
    Presentable make();
}
