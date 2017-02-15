package com.progressoft.security.app.layout.client.presenters;

import com.progressoft.security.app.layout.client.requests.AddHeaderItemClientRequest;
import com.progressoft.security.app.layout.client.requests.AddMenuItemClientRequest;
import com.progressoft.security.app.layout.client.requests.ShowContentClientRequest;
import com.progressoft.security.app.layout.client.requests.ShowSideContentClientRequest;
import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
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
    public void showContent(LayoutItem item) {
        new ShowContentClientRequest(item).send();
    }

    @Override
    public void showSideContent(LayoutItem content) {
        new ShowSideContentClientRequest(content).send();
    }
}
