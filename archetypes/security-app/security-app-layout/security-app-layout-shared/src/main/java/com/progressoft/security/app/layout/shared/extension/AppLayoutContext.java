package com.progressoft.security.app.layout.shared.extension;


import org.akab.engine.core.api.shared.extension.Context;

public interface AppLayoutContext extends Context {

    void addHeaderItem(LayoutItem item);
    void addMenuItem(LayoutItem item);
    void showContent(LayoutItem content);
    void showSideContent(LayoutItem content);
}
