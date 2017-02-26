package com.progressoft.security.app.layout.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class FakeAppLayoutContribution implements Contribution<AppLayoutExtensionPoint>{

    private AppLayoutContext context;

    @Override
    public void contribute(AppLayoutExtensionPoint extensionPoint) {
        this.context=extensionPoint.context();
    }

    public AppLayoutContext getContext() {
        return context;
    }

    public void addHeaderItem(FakeLayoutItem fakeLayoutItem) {
        context.addHeaderItem(fakeLayoutItem);
    }

    public void addMenuItem(FakeLayoutItem fakeLayoutItem) {
        context.addMenuItem(fakeLayoutItem);
    }

    public void showContent(FakeLayoutItem fakeLayoutItem) {
        context.showContent(fakeLayoutItem);
    }

    public void setSideContent(FakeLayoutItem fakeLayoutItem) {
        context.setSideContent(fakeLayoutItem);
    }

    public void showRightPanel() {
        context.showRightPanel();
    }

    public void hideRightPanel() {
        context.hideRightPanel();
    }

    public void setFabHandler(FabHandler fabHandler) {
        context.setFabHandler(fabHandler);
    }

    public void addFabItem(FakeLayoutItem fakeLayoutItem) {
        context.addFabItem(fakeLayoutItem);
    }
}
