package com.progressoft.security.app.layout.client;

import com.progressoft.security.app.layout.client.views.DefaultLayoutView;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

import java.util.ArrayList;
import java.util.List;

public class LayoutViewSpy extends DefaultLayoutView {
    public List<LayoutItem> headerItems=new ArrayList<>();
    public List<LayoutItem> menuItems=new ArrayList<>();
    public LayoutItem content;
    public LayoutItem sideContent;

    @Override
    public void addHeaderItem(LayoutItem headerItem) {
        super.addHeaderItem(headerItem);
        headerItems.add(headerItem);
    }

    @Override
    public void addMenuItem(LayoutItem menuItem) {
        super.addMenuItem(menuItem);
        menuItems.add(menuItem);
    }

    @Override
    public void showContent(LayoutItem contentItem) {
        super.showContent(contentItem);
        this.content=contentItem;
    }

    @Override
    public void showSideContent(LayoutItem content) {
        super.showSideContent(content);
        this.sideContent=content;
    }
}
