package com.progressoft.security.layout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.progressoft.security.layout.client.presenters.AuthenticationLayoutPresenter;
import gwt.material.design.client.ui.MaterialPanel;
import org.akab.engine.core.api.client.annotations.UiView;

@UiView(presentable = AuthenticationLayoutPresenter.class)
public class DefaultAuthenticationLayoutView extends Composite implements AuthenticationLayoutView{

    interface DefaultAuthenticationLayoutViewUiBinder extends UiBinder<HTMLPanel, DefaultAuthenticationLayoutView> {
    }

    private static DefaultAuthenticationLayoutViewUiBinder ourUiBinder = GWT.create(DefaultAuthenticationLayoutViewUiBinder.class);

    @UiField
    MaterialPanel mainPanel;

    public DefaultAuthenticationLayoutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void showView(IsWidget view) {
        mainPanel.add(view);
    }
}