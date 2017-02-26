package com.progressoft.security.logout.client;

import com.progressoft.security.logout.client.views.DefaultCompositeLogoutView;
import com.progressoft.security.logout.client.views.HeaderLogoutView;
import com.progressoft.security.logout.client.views.LogoutView;
import com.progressoft.security.logout.client.views.MenuLogoutView;

public class LogoutViewSpy extends DefaultCompositeLogoutView {

    public void clickHeaderLogout() {
        ((HeaderLogoutView)super.getView(LogoutView.HEADER)).logUserOut();
    }

    public void clickMenuLogout() {
        ((MenuLogoutView)super.getView(LogoutView.MENU)).logUserOut();
    }
}
