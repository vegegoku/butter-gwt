package com.progressoft.security.authentication.client.views;

import org.akab.engine.core.api.client.mvp.view.View;

public interface AuthenticationView extends View{
    void setWelcomeMessage(String welcomeMessage);
}