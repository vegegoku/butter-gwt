package com.progressoft.security.logout.client.views;

import com.progressoft.security.logout.client.presenters.LogoutHandler;
import org.akab.engine.core.api.client.mvp.view.CompositeView;

public interface CompositeLogoutView extends CompositeView<LogoutView> {
    void onLoggedOut();
    void setLogoutHandler(LogoutHandler logoutHandler);
}
