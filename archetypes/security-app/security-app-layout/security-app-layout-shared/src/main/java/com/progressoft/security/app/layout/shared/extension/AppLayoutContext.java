package com.progressoft.security.app.layout.shared.extension;


import org.akab.engine.core.api.shared.extension.Context;

public interface AppLayoutContext extends Context {

    void addHeaderItem(LayoutItem item);
    void addMenuItem(LayoutItem item);
    void addMenuItem(LayoutItem item, int beforeIndex);
    void showContent(LayoutItem content);
    void setSideContent(LayoutItem content);
    void showRightPanel();
    void hideRightPanel();
    void setFabHandler(FabHandler fabHandler);
    void addFabItem(LayoutItem fabItem);
}
