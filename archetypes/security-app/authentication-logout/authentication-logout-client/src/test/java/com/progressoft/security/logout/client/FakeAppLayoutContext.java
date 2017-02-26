package com.progressoft.security.logout.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

public class FakeAppLayoutContext implements AppLayoutContext {
    public LayoutItem logoutButton;
    public LayoutItem logoutMenu;

    @Override
    public void addHeaderItem(LayoutItem item) {
        this.logoutButton=item;
    }

    @Override
    public void addMenuItem(LayoutItem item, int beforeIndex) {
        this.logoutMenu=item;
    }

    @Override
    public void addMenuItem(LayoutItem item) {
        this.logoutMenu=item;
    }

    @Override
    public void showContent(LayoutItem content) {

    }

    @Override
    public void setSideContent(LayoutItem content) {

    }

    @Override
    public void showRightPanel() {

    }

    @Override
    public void hideRightPanel() {

    }

    @Override
    public void setFabHandler(FabHandler fabHandler) {

    }

    @Override
    public void addFabItem(LayoutItem fabItem) {

    }
}
