package com.progressoft.security.otp.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import com.progressoft.security.otp.client.presenters.VerifyHandler;
import gwt.material.design.client.ui.*;
import org.akab.engine.core.api.client.annotations.UiView;

@UiView(presentable = OtpPresenter.class)
public class DefaultOtpView extends Composite implements OtpView {

    public static final String REQUIRED = "Required";
    protected VerifyHandler verifyOtpHandler;

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
        root = ourUiBinder.createAndBindUi(this);
        initWidget(root);
    }

    @UiHandler("verifyOtpButton")
    void onVerifyButtonPressed(ClickEvent event) {
        this.verifyOtpHandler.handle(getOtpCode());
    }

    @Override
    public OtpView show() {
        this.otpCode.setFocus(true);
        return this;
    }

    @Override
    public void addVerifyHandler(VerifyHandler handler) {
        this.verifyOtpHandler = handler;
    }

    @Override
    public void invalidateField() {
        this.otpCode.setError(REQUIRED);
    }

    protected String getOtpCode() {
        return this.otpCode.getValue();
    }

    public void setOtpCode(String otpCode) {
        this.otpCode.setValue(otpCode);
    }

}