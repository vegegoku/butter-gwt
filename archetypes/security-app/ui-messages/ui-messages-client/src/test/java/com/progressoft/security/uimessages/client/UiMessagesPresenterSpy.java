package com.progressoft.security.uimessages.client;

import com.progressoft.security.uimessages.client.presenters.DefaultUiMessagesPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class UiMessagesPresenterSpy extends DefaultUiMessagesPresenter{


    @Override
    protected String getConcrete() {
        return DefaultUiMessagesPresenter.class.getCanonicalName();
    }
}
