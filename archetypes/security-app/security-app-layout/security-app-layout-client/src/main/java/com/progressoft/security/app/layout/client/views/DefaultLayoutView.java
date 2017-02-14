package com.progressoft.security.app.layout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialNavSection;
import gwt.material.design.client.ui.MaterialSideNav;
import org.akab.engine.core.api.client.annotations.UiView;

@UiView(presentable = AppLayoutPresenter.class)
public class DefaultLayoutView extends Composite implements LayoutView{

    interface DefaultLayoutViewUiBinder extends UiBinder<HTMLPanel, DefaultLayoutView> {
    }

    private static DefaultLayoutViewUiBinder ourUiBinder = GWT.create(DefaultLayoutViewUiBinder.class);

    @UiField
    MaterialNavSection headerBar;

    @UiField
    MaterialSideNav menuPanel;

    @UiField
    MaterialContainer mainContainer;

    public DefaultLayoutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void addHeaderItem(LayoutItem headerItem) {
        headerBar.add(headerItem);
    }

    @Override
    public void addMenuItem(LayoutItem menuItem) {
        menuPanel.add(menuItem);
    }

    @Override
    public void showContent(LayoutItem contentItem) {
        mainContainer.clear();
        mainContainer.add(contentItem);
    }

}