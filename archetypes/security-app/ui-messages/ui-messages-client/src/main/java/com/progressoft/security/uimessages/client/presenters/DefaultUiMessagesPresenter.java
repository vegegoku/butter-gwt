package com.progressoft.security.uimessages.client.presenters;

import com.progressoft.security.uimessages.shared.extension.UiMessagesExtensionPoint;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.uimessages.client.views.UiMessagesView;
import org.akab.engine.core.api.shared.extension.MainContext;

@Presenter
public class DefaultUiMessagesPresenter extends BaseClientPresenter<UiMessagesView> implements UiMessagesPresenter {

    @Override
    public void onApplyContributionToUiMessagesExtensionPoint(MainContext mainContext) {
        Contributions.apply(UiMessagesExtensionPoint.class,
                (UiMessagesExtensionPoint) DefaultUiMessagesContext::new);
        mainContext.appendWidgetToRoot(view);
    }

    @Override
    public void onShowErrorMessage(String message, String details) {
        view.showError(message, details);
    }
}