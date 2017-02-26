package com.progressoft.security.logout.client.views;

import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import com.progressoft.security.logout.client.presenters.LogoutHandler;
import org.akab.engine.core.api.client.mvp.view.View;

public interface LogoutView extends View, LayoutItem{

    String HEADER="HEADER";
    String MENU="MENU";

    void setLogoutHandler(LogoutHandler logoutHandler);



}