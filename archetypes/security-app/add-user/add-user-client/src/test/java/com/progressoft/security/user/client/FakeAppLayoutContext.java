package com.progressoft.security.user.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

public class FakeAppLayoutContext implements AppLayoutContext{
    private LayoutItem newUserButton;

    @Override
    public void addHeaderItem(LayoutItem item, int order) {

    }

    @Override
    public void addMenuItem(LayoutItem item, int order) {

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
        this.newUserButton=fabItem;
    }

    public LayoutItem getAddNewUserButton() {
        return this.newUserButton;
    }
}
