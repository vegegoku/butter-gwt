package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.login.client.presenters.LoginPresenter;

@UiView(presentable = LoginPresenter.class)
public class DefaultLoginView extends Composite implements LoginView{

    interface DefaultLoginViewUiBinder extends UiBinder<HTMLPanel, DefaultLoginView> {
    }

    private static DefaultLoginViewUiBinder ourUiBinder = GWT.create(DefaultLoginViewUiBinder.class);

    @UiField
    DivElement mainDiv;

    public DefaultLoginView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        mainDiv.setInnerText(welcomeMessage);
    }
}