package com.progressoft.security.otp.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.annotations.UiView;

@UiView(presentable = OtpPresenter.class)
public class DefaultOtpView extends Composite implements OtpView{

    interface DefaultOtpViewUiBinder extends UiBinder<MaterialRow, DefaultOtpView> {
    }

    @UiField
    MaterialRow root;

    @UiField
    MaterialButton verifyOtpButton;
    @UiField
    MaterialTextBox otpCode;


    private static DefaultOtpViewUiBinder ourUiBinder = GWT.create(DefaultOtpViewUiBinder.class);

    public DefaultOtpView() {
        root=ourUiBinder.createAndBindUi(this);
        initWidget(root);
    }

    @Override
    public OtpView show() {
        this.otpCode.setFocus(true);
        return this;
    }
}