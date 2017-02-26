package com.progressoft.security.app.layout.client.presenters;

import com.progressoft.security.app.layout.client.requests.*;
import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

public class DefaultAppLayoutContext implements AppLayoutContext {
    @Override
    public void addHeaderItem(LayoutItem item) {
        new AddHeaderItemClientRequest(item).send();
    }

    @Override
    public void addMenuItem(LayoutItem item) {
        new AddMenuItemClientRequest(item).send();
    }

    @Override
    public void addMenuItem(LayoutItem item, int beforeIndex) {
        new AddMenuItemClientRequest(item, beforeIndex).send();
    }

    @Override
    public void showContent(LayoutItem item) {
        new ShowContentClientRequest(item).send();
    }

    @Override
    public void showRightPanel() {
        new ShowRightPanelClientRequest().send();
    }

    @Override
    public void hideRightPanel() {
        new HideRightPanelClientRequest().send();
    }

    @Override
    public void setSideContent(LayoutItem content) {
        new SetSideContentClientRequest(content).send();
    }

    @Override
    public void setFabHandler(FabHandler fabHandler) {
        new SetFabHandlerClientRequest(fabHandler).send();
    }

    @Override
    public void addFabItem(LayoutItem fabItem) {
        new AddFabItemClientRequest(fabItem).send();
    }
}
