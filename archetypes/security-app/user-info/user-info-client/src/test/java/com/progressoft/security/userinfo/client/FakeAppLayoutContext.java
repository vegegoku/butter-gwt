package com.progressoft.security.userinfo.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

public class FakeAppLayoutContext implements AppLayoutContext{
    private LayoutItem userNameHeaderItem;

    @Override
    public void addHeaderItem(LayoutItem item) {
        this.userNameHeaderItem=item;
    }

    @Override
    public void addMenuItem(LayoutItem item) {

    }

    @Override
    public void addMenuItem(LayoutItem item, int beforeIndex) {

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

    public LayoutItem getUserNameHeaderItem() {
        return this.userNameHeaderItem;
    }
}
