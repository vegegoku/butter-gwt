package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialSplashScreen;
import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.login.client.presenters.LoginPresenter;

@UiView(presentable = LoginPresenter.class)
public class DefaultLoginView implements LoginView{

    @Override
    public Widget asWidget() {
        return mainPanel;
    }

    interface DefaultLoginViewUiBinder extends UiBinder<Widget, DefaultLoginView> {
    }

    private static DefaultLoginViewUiBinder ourUiBinder = GWT.create(DefaultLoginViewUiBinder.class);

    Widget mainPanel;

    public DefaultLoginView() {
        mainPanel=ourUiBinder.createAndBindUi(this);
    }

    @Override
    public void setWelcomeMessage(String welcomeMessage) {

//        RootPanel.get().add(mainPanel);
    }
}