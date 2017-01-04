package com.progressoft.security.login.client.views;

import com.google.gwt.event.dom.client.HasClickHandlers;
import org.akab.engine.core.api.client.mvp.view.View;

public interface LoginView extends View{
    HasClickHandlers loginTrigger();
}