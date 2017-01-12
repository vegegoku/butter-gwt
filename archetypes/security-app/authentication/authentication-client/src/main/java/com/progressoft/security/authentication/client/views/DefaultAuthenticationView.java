package com.progressoft.security.authentication.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;

@UiView(presentable = AuthenticationPresenter.class)
public class DefaultAuthenticationView extends Composite implements AuthenticationView{

    interface DefaultAuthenticationViewUiBinder extends UiBinder<HTMLPanel, DefaultAuthenticationView> {
    }

    private static DefaultAuthenticationViewUiBinder ourUiBinder = GWT.create(DefaultAuthenticationViewUiBinder.class);

    @UiField
    DivElement mainDiv;

    public DefaultAuthenticationView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void showErrorMessage(String message) {
        // TODO: 1/11/17 to be implemented after we finish the unit testing.
    }

}