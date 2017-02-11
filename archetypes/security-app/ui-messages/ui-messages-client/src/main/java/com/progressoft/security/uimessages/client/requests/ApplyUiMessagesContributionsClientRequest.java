package com.progressoft.security.uimessages.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.uimessages.client.presenters.UiMessagesPresenter;

import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.shared.extension.MainContext;

@Request
public class ApplyUiMessagesContributionsClientRequest extends ClientRequest<UiMessagesPresenter> {

    private final MainContext mainContext;

    public ApplyUiMessagesContributionsClientRequest(MainContext context) {
        this.mainContext=context;
    }

    @Override
    protected void process(UiMessagesPresenter presenter) {
        presenter.onApplyContributionToUiMessagesExtensionPoint(mainContext);
    }
}