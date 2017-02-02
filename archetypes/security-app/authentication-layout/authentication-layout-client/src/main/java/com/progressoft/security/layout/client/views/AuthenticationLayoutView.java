package com.progressoft.security.layout.client.views;

import com.google.gwt.user.client.ui.IsWidget;
import org.akab.engine.core.api.client.mvp.view.View;

public interface AuthenticationLayoutView extends View{
    void showView(IsWidget view);
    void hideView(IsWidget view);
}