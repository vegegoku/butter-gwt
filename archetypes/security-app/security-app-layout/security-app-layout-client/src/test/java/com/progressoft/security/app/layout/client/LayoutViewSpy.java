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
    public String rightPanelVisibility="";
    public List<LayoutItem> fabItems = new ArrayList<>();

    @Override
    public void addHeaderItem(LayoutItem headerItem) {
        super.addHeaderItem(headerItem);
        headerItems.add(headerItem);
    }

    @Override
    public void addMenuItem(LayoutItem menuItem, int beforeIndex) {
        super.addMenuItem(menuItem, beforeIndex);
        menuItems.add(menuItem);
    }

    @Override
    public void showContent(LayoutItem contentItem) {
        super.showContent(contentItem);
        this.content=contentItem;
    }

    @Override
    public void setSideContent(LayoutItem content) {
        super.setSideContent(content);
        this.sideContent=content;
    }

    @Override
    public void showRightPanel() {
        super.showRightPanel();
        rightPanelVisibility+="true";
    }

    @Override
    public void hideRightPanel() {
        super.hideRightPanel();
        rightPanelVisibility+="false";
    }

    public void clickOnFab() {
        super.fireFabClick();
    }

    @Override
    public void addFabItem(LayoutItem fabItem) {
        super.addFabItem(fabItem);
        fabItems.add(fabItem);
    }
}
