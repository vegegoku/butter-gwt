package com.progressoft.security.uimessages.client.views;

import org.akab.engine.core.api.client.mvp.view.View;

public interface UiMessagesView extends View{
    void showError(String message, String details);
}