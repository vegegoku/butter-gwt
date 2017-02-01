package com.progressoft.security.otp.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.otp.client.presenters.OtpPresenter;

@UiView(presentable = OtpPresenter.class)
public class DefaultOtpView extends Composite implements OtpView{

    interface DefaultOtpViewUiBinder extends UiBinder<HTMLPanel, DefaultOtpView> {
    }

    private static DefaultOtpViewUiBinder ourUiBinder = GWT.create(DefaultOtpViewUiBinder.class);

    public DefaultOtpView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void show() {

    }
}