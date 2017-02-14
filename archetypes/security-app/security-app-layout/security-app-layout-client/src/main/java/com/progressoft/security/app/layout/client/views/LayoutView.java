package com.progressoft.security.app.layout.client.views;

import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import org.akab.engine.core.api.client.mvp.view.View;

public interface LayoutView extends View{
    void addHeaderItem(LayoutItem headerItem);

    void addMenuItem(LayoutItem menuItem);

    void showContent(LayoutItem contentItem);

    void showSideContent(LayoutItem content);
}