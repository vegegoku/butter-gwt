package com.progressoft.security.dashboard.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

public class FakeAppLayoutContext implements AppLayoutContext {

    private LayoutItem dashboardHeaderItem;

    @Override
    public void addHeaderItem(LayoutItem item) {
        this.dashboardHeaderItem=item;
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

    public LayoutItem getDashboardHeaderItem() {
        return this.dashboardHeaderItem;
    }
}
