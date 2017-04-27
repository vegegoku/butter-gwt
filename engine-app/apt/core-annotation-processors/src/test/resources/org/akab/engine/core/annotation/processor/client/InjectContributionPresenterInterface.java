package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.InjectContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface InjectContributionPresenterInterface extends Presentable {

    @InjectContext(extensionPoint = MainExtensionPoint.class)
    void onMainExtensionPointContextReceived(MainContext context);
}