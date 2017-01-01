package org.akab.engine.app.test;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;

public interface TestPresenterFactory {
    Presentable make();
}
