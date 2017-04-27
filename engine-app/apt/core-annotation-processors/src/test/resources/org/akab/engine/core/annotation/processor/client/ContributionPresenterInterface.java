package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainContext;

public interface ContributionPresenterInterface extends Presentable {

    void onMainContextReceived(MainContext context);
}