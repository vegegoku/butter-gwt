package com.progressoft.security.uimessages.client.presenters;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainContext;

public interface UiMessagesPresenter extends Presentable{
    void onApplyContributionToUiMessagesExtensionPoint(MainContext mainContext);

    void onShowErrorMessage(String message, String details);
}